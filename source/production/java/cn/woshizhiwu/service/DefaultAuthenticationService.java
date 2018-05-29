package cn.woshizhiwu.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cn.woshizhiwu.entity.User;
import cn.woshizhiwu.repository.UserInfoRepository;

@Service
public class DefaultAuthenticationService implements AuthenticationService{
	
	@Inject UserInfoRepository userInfoRepository;

	@Override
	public boolean checkLoginInfo(String email, String password) {
		UserInfoRepository uir = this.userInfoRepository;
		if(uir.getId(email) != 0) {
		    if(password.equals(uir.getUser(uir.getId(email)).getPassword())) { 
		    	    return true;
		    }
		 }
		return false;
	}

	@Override
	public boolean saveUser(User user) {
		return this.userInfoRepository.add(user);
	}

	@Override
	public User getUser(String email) {
		User user = this.userInfoRepository.getUser(this.userInfoRepository.getId(email));
		return user;
	}

	@Override
	public User getUser(long id) {
		User user = this.userInfoRepository.getUser(id);
		return user;
	}
	

}
