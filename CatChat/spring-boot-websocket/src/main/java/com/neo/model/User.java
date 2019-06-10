package com.neo.model;

import com.neo.enums.UserSexEnum;

import java.io.Serializable;

/**  
 *    
 * @Description:  
 * @author shuangling.mao 
 * @date 2019/6/10 15:10  
 * @param   
 * @return   
 */ 
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String password;
	private String email;
	private String nickname;
	private String regTime;
	private UserSexEnum userSex;

	public User() {
		super();
	}
	public User(String email, String nickname, String password, String userName, String regTime) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.userName = userName;
		this.regTime = regTime;
	}
	public User(String userName, String password, UserSexEnum userSex) {
		super();
		this.password = password;
		this.userName = userName;
		this.userSex = userSex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", nickname='" + nickname + '\'' +
				", regTime='" + regTime + '\'' +
				'}';
	}
}