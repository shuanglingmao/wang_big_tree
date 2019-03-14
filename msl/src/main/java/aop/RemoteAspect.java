package aop;

import exception.BizException;
import exception.BusinessRuntimeException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import util.JsonUtil;
import util.ParamValidator;

import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;

/**
 * description: 拦截远程服务
 * <li>参数校验</li>
 * <li>记录参数日志</li>
 * <li>封装异常结果并返回</li>
 * <li>记录返回参数日志</li>
 *
 * @author yongjun.ji
 * @date 2018/7/12 16:10
 */
@Aspect
@Component
@Order(1)
public class RemoteAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteAspect.class);
    
//    @Pointcut("execution(public * com.zuche.framework.remote.AbstractRemoteService.*(..))")
    @Pointcut("execution(public * com.myself.*(..))")
    public void pointRemoteService() {
    }

    
    enum ExceptionType {
        /** 没有异常 */
        NON,
        /** 业务异常 */
        BIZ_EXCEPTION,
        /** 服务异常 */
        SERVICE_EXCEPTION;
    }
    @Around("pointRemoteService()")
    public Object aroundRemote(ProceedingJoinPoint jp) {
        Object[] args = jp.getArgs();
        RpcInvocation invocation = (RpcInvocation)args[0];
        // 备份参数，后续可能会修改参数
        String jsonArgs = JsonUtil.transferJson(invocation.getArguments());
        try {
            // 1.校验参数
            validateArgs(invocation);
            // 2.执行服务
            Object obj = jp.proceed();
            // 3.记录入参和出参
            doLog(invocation, jsonArgs, obj, ExceptionType.NON);
            return obj;
        } catch (Throwable t) {
            // 返回异常result
            return failed(invocation, jsonArgs, t);
        }
    }
    
    private void validateArgs(RpcInvocation rpcInvocation) throws BizException {
        Object[] arguments = rpcInvocation.getArguments();
        if (arguments != null) {
            for (Object argument : arguments) {
                ParamValidator.validate(argument);
            }
        }
    }

    /**
     * 返回异常结果
     *
     * @param rpcInvocation 服务参数
     * @param jsonArgs
     * @param t 异常
     * @return result
     */
    private RemoteResultVO failed(RpcInvocation rpcInvocation, String jsonArgs, Throwable t) {
        Result result = new Result();
        Throwable cause = t.getCause();
        if (cause == null) {
            cause = t;
        }
        if (cause instanceof InvocationTargetException) {
            Throwable target = ((InvocationTargetException) cause).getTargetException();
            if (target != null) {
                if (target instanceof BusinessRuntimeException || target instanceof ValidationException) {
                    // 业务异常
                    result.setStatus(RemoteCommConstant.REMOTE_BUSINESS_ERROR_STATUS);
                    String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(target);
                    String msg = null;
                    // 只记第一行
                    if (stackTrace != null && stackTrace.length > 0) {
                        msg = stackTrace[0];
                        if (stackTrace.length > 1) {
                            msg += "\r\n" + stackTrace[1];
                        }
                    }
                    doLog(rpcInvocation, jsonArgs, msg, ExceptionType.BIZ_EXCEPTION);
                } else {
                    // 服务异常
                    result.setStatus(RemoteCommConstant.REMOTE_SERVICE_ERROR_STATUS);
                    doLog(rpcInvocation, jsonArgs, target, ExceptionType.SERVICE_EXCEPTION);
                }
                result.setMsg(target.getMessage());
            }
        } else if (cause instanceof BusinessRuntimeException || cause instanceof ValidationException) {
            // 参数校验异常，不记堆栈记录
            result.setStatus(RemoteCommConstant.REMOTE_BUSINESS_ERROR_STATUS);
            result.setMsg(cause.getMessage());
            doLog(rpcInvocation, jsonArgs, "参数校验不通过：" + cause.getMessage(), ExceptionType.BIZ_EXCEPTION);
        } else {
            result.setStatus(RemoteCommConstant.REMOTE_SERVICE_ERROR_STATUS);
            result.setMsg(t.getMessage());
            doLog(rpcInvocation, jsonArgs, t, ExceptionType.SERVICE_EXCEPTION);
        }
        RemoteResultVO resultVO = new RemoteResultVO();
        resultVO.setResult(result);
        return resultVO;
    }

    /**
     * 记录日志
     * @param invocation rpc执行对象 
     * @param jsonArgs json格式入参
     * @param returnVal 出参
     * @param exceptionType
     */
    private void doLog(RpcInvocation invocation, String jsonArgs, Object returnVal, ExceptionType exceptionType) {
        try {
            // 过滤不需要记录日志的服务
            if (!isShow(exceptionType, invocation)) {
                return;
            }
            String serviceName = invocation.getServiceid();
            // 记录Hbase日志
            switch (exceptionType) {
                case NON:
//                    HBaseLogUtil.addRemoteServiceLog(serviceName, jsonArgs, returnVal, "远程服务参数日志");
                    break;
                case BIZ_EXCEPTION:
//                    HBaseLogUtil.addRemoteServiceBizExceptionLog(serviceName, jsonArgs, (String) returnVal, "远程服务业务异常日志");
                    LOGGER.error("执行服务{}业务异常，参数：{}，异常信息：{}", serviceName, jsonArgs, returnVal);
                    break;
                case SERVICE_EXCEPTION:
                    String fullStackTrace = null;
//                    String fullStackTrace = ExceptionUtils.getFullStackTrace((Throwable) returnVal);
//                    HBaseLogUtil.addRemoteServiceExceptionLog(serviceName, jsonArgs, fullStackTrace, "远程服务服务异常日志");
                    LOGGER.error("执行服务{}服务异常，参数：{}，异常信息：{}", serviceName, jsonArgs, fullStackTrace);
                    break;
                default:break;
            }
        } catch (Throwable t) {
            LOGGER.error("记录日志异常：" + t.getMessage(), t);
        }
    }

    /**
     * 判断是否显示日志
     * <p>
     *     <li>异常日志需要显示</li>
     *     <li>不在远程服务黑名单中的服务需要显示</li>
     * </p>
     * @param exceptionType 
     * @param invocation rpc参数
     * @return 是否显示日志
     */
    private boolean isShow(ExceptionType exceptionType, RpcInvocation invocation) {
        if (!exceptionType.equals(ExceptionType.NON)) {
            return true;
        }
        return true;
    }
    
}
