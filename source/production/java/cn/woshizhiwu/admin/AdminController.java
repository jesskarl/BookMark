package cn.woshizhiwu.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.woshizhiwu.entity.Url;
import cn.woshizhiwu.entity.User;
import cn.woshizhiwu.repository.UrlRepository;
import cn.woshizhiwu.repository.UserInfoRepository;

@Controller
public class AdminController {
	@Inject UrlRepository urlRepository;
	@Inject UserInfoRepository userInfoRepository;
	
	@RequestMapping(value = "user/admin")
	public String admin(Map<String,Object> model, 
			               @RequestParam(value = "page", defaultValue = "0") int page,
			               @RequestParam(value = "tag",required = false) String tag,
			               @RequestParam(value = "deleteUrlId", defaultValue = "0") long id) {
		List<Url> urlList;
		List<User> userList;
		switch(page) {
		case 0 : 
			if(tag == null) {
			urlList = this.urlRepository.getAll(); 
			  model.put("title", "ALL URL"); model.put("urlList", urlList); 
			  model.put("nums", urlList.size());break;
		  }else {
			  urlList = this.urlRepository.getAll(tag);
			  model.put("title", "ALL URL"); model.put("urlList", urlList); 
			  model.put("tag", tag); 
			  model.put("nums", urlList.size());break;
		   }
		case 1 : userList = this.userInfoRepository.getAllUser(); 
		  model.put("title", "ALL User"); model.put("userList", userList); 
		  model.put("nums", userList.size()); break;
		}
		
		if(id != 0) {
			this.urlRepository.delete(id);
		}
		return "admin/admin";
	}

}
