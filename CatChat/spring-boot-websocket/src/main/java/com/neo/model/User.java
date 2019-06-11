package com.neo.model;

import com.neo.enums.UserSexEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**  
 *    
 * @Description:  
 * @author shuangling.mao 
 * @date 2019/6/10 15:19
 * @param   
 * @return   
 */
@Data
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键ID*/
	private Long id;
	/**用户名*/
	private String userName;
	/**密码*/
	private String passWord;
	/**性别*/
	private UserSexEnum userSex;
	/**昵称*/
	private String nickName;
	/**电话号码*/
	private String phoneNum;
	/**级别*/
	private Integer level;
	/**地址*/
	private String address;
	/**注册时间*/
	private String createTime;
	/**邮箱*/
	private String email;
	/**余额*/
	private Double amount;
	/**积分*/
	private Integer integral;
	/**加密盐值*/
	private String salt;
	/**头像*/
	private String chatHeadImg;
}