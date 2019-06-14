package com.neo.model;

/**
 * Description: 远程常量
 * All Rights Reserved.
 *
 * @version 1.0  2014-11-15 下午1:17:27  by 胡明华（mh.hu@zuche.com）创建
 */
public class RemoteCommConstant {

	/**
	 * 远程服务成功
	 */
	public final static int REMOTE_SUCCES_STATUS = 0;

	/**
	 * 远程业务失败
	 */
	public final static int REMOTE_BUSINESS_ERROR_STATUS = -1;

	/**
	 * 远程运行时异常
	 */
	public final static int REMOTE_SERVICE_ERROR_STATUS = -2;
	   
    /**
     * 特殊情况使用:自动退款业务中使用
     */
    public final static int REMOTE_PART_SUCCES_STATUS = 1;

	/**
	 * Description: 远程服务返回结果常量类
	 * All Rights Reserved.
	 * @version 1.0  2014-11-17 上午11:06:35  by 胡明华（mh.hu@zuche.com）创建
	 */
	public enum RemoteCommonResult {
		SUCCESS("成功", 0),
		ERROR_IN_BUSINESS("业务异常", -1),
		ERROR_IN_SERVER("服务器内部错误", -2),
		CALL_OFTEN("请求频繁", -900),
		REMOTE_ILLEGALACCESS__EXCEPTION("非法访问异常", -910),
		REMOTE_UNUSE__EXCEPTION("此远程服务已废弃!", -920);

		RemoteCommonResult(String name, int value) {
			this.name = name;
			this.value = value;
		}

		private String name;
		private int    value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}


		public int getIndex() {
			return value;
		}

	}

}
