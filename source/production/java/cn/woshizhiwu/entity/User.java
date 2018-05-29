package cn.woshizhiwu.entity;

import javax.validation.constraints.Min;

import cn.woshizhiwu.validation.Email;
import cn.woshizhiwu.validation.NotBlank;

public class User {
	private long id;
	
	@Email(message = "邮箱格式错误")
	private String email;
	
	@NotBlank(message = "用户名不得为空")
	private String username;
	
	@NotBlank(message = "密码不得为空")
	@Min(value = 8L, message = "密码不得短于8位")
	private String password;
	
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
