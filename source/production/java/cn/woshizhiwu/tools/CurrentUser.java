package cn.woshizhiwu.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.woshizhiwu.entity.Anonymous;
import cn.woshizhiwu.entity.User;
import cn.woshizhiwu.repository.InmeUserInfoRepository;


public class CurrentUser{
	
	HttpServletRequest req;
	HttpServletResponse res;
	HttpSession session;
	

	public CurrentUser(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
		this.session = req.getSession();
	}

	public boolean isLogin() {
		Object isLogin = this.session.getAttribute("isLogin");
		if(isLogin != null && (boolean)isLogin) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public User getUser() {
		String email = (String) this.session.getAttribute("userEmail");
		if(email != null) {
		  InmeUserInfoRepository re = new InmeUserInfoRepository();
			User user = re.getUser(re.getId(email));
			return user;
		}
		User anonymous = new Anonymous();
		return anonymous;
	}
	
	public long getId() {
		String email = (String) this.session.getAttribute("userEmail");
		if(email != null) {
			  InmeUserInfoRepository re = new InmeUserInfoRepository();
				long id = re.getId(email);
				return id;
			}
		return 0;
	}

}
