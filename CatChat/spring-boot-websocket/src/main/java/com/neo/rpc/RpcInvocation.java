package com.neo.rpc;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Map;

/**
 * @Description: rpc统一调用封装
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/4 0004
 * @Author 毛双领 <shuangling.mao>
 */
public class RpcInvocation implements Serializable{
    private static final long serialVersionUID = -5703010129117812116L;

    /** 方法名 */
    private String methodName;

    /** 1 */
    private String serviceId;

    private Class<?>[] parameterTypes;

    private Object[] arguments;

    public RpcInvocation() {
    }

    public RpcInvocation(String serviceId, HttpSession session, Map<String, String> attachments) {
        this.serviceId = serviceId;
        this.session = session;
        this.attachments = attachments;
    }

    private HttpSession session;

    private Map<String, String> attachments;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }
}
