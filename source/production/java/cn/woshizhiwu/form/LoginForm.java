package cn.woshizhiwu.form;

import javax.validation.constraints.Min;

import cn.woshizhiwu.validation.Email;
import cn.woshizhiwu.validation.NotBlank;

public class LoginForm {
	
	@Email(message = "邮箱格式错误")
	private String email;
	
	@NotBlank(message = "密码不得为空")
	@Min(value = 8L, message = "密码不得短于8位")
	private String password;
	
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
