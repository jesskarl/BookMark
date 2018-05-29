package cn.woshizhiwu.form;

import javax.validation.constraints.Pattern;

import cn.woshizhiwu.validation.Email;
import cn.woshizhiwu.validation.NotBlank;

public class RegisterForm {
	@Email(message = "邮箱格式错误")
	private String email;
	@NotBlank(message = "用户名不得为空")
	private String username;
	@NotBlank(message = "密码不得为空")
	@Pattern( message = "密码最少8位，并且包含大小写与特殊字符", regexp = 
	    "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
	private String password;
	@NotBlank(message = "确认密码不得为空")
	private String password2;
	
	
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public String getPassword2() {
		return this.password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}


}
