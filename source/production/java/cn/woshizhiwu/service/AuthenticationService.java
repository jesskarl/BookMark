package cn.woshizhiwu.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import cn.woshizhiwu.entity.User;
import cn.woshizhiwu.validation.Email;

public interface AuthenticationService {
	
	public boolean checkLoginInfo(String username,String password);

	public boolean saveUser(@NotNull(message = "用户不得为空")
    @Valid User user);
	
	public User getUser(@Email(message = "邮箱格式错误") String email);
	public User getUser(@Min(value = 1L,
            message = "id不得小于1") long id);

}
