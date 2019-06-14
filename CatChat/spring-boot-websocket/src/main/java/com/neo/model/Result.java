package com.neo.model;


import java.io.Serializable;

/**
 * Description:<pre>
 * Result对象包含协议属性：
 * re:业务正常返回的消息体,每个服务使用专用Re对象，不可复用。属性命名规则同数据库命名。支持数据结构包装，例如：List<Re>，Set<Re>。
 * status:服务状态码，为int类型， 0 为成功，-1为业务异常，-2为服务异常。
 * code:业务语义状态码，用于做业务语义路由，和msg取其一设值即可。
 * msg:描述信息。用于返回业务异常信息和服务异常信息，不可用于设置日志堆栈信息，和code取其一设值即可。举例：业务信息： msg = "附近暂无司机" ，服务异常信息：msg="订单创建失败".
 * </pre>
 *
 * @param <T>
 * @Version1.0 2017/5/10 15:01 by 王海涛（ht.wang05@zuche.com）创建
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = -6029022489278479070L;

	/**
	 * 服务状态码，为int类型， 0 为成功，-1为业务异常，-2为服务异常。
     * {@link RemoteCommConstant}
	 */
	private int status = RemoteCommConstant.REMOTE_SERVICE_ERROR_STATUS;
	/**
	 * 描述信息。用于返回业务异常信息和服务异常信息，不可用于设置日志堆栈信息，和code取其一设值即可。举例：业务信息： msg = "附近暂无司机" ，服务异常信息：msg="订单创建失败".
	 */
	private String msg;

    /**
     *业务语义状态码，用于做业务语义路由，和msg取其一设值即可
     */
    private String code;

    /**
	 * 业务正常返回的消息体,每个服务使用专用Re对象，不可复用。属性命名规则同数据库命名。支持数据结构包装，例如：List<Re>，Set<Re>。
	 */
	private T re;

	
	public Result(){
		
	}
	
	public Result(Result result){
		this.re = (T) result;
	}
	 
	public Result(T re, int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
		this.re = re;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getRe() {
		return re;
	}

	public void setRe(T re) {
		this.re = re;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
