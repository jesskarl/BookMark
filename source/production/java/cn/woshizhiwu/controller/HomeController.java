package cn.woshizhiwu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.woshizhiwu.entity.Url;
import cn.woshizhiwu.service.UrlService;
import cn.woshizhiwu.tools.CurrentUser;

@Controller
@RequestMapping("/user")
public class HomeController {
	
	@Inject UrlService urlService;
/*all = 0;
fav = 1;
ven = 2;
arc = 3;
*/
	
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public String userHome(Map<String,Object> model, HttpServletRequest req,HttpServletResponse res,
			                  @RequestParam(value = "category", defaultValue = "0") int category,
			                  @RequestParam(value = "tag",required = false) String tag) {
		if(category > 3 || category < 0) {
			category = 0;
		}
		CurrentUser currentUser = new CurrentUser(req,res);
		long id = currentUser.getId();
		List<Url> urlList = new ArrayList<Url>();
		if(tag == null) {
			urlList.addAll(this.urlService.getUserUrl(id,category));
		}
		else if(tag != null) {
			urlList.addAll(this.urlService.getUserTagUrl(id,tag,category));
			model.put("tag", tag);
		}
		
		model.put("urlList", urlList);
		
		switch(category) {
		case 0 : model.put("title", "所有");break;
		case 1 : model.put("title", "收藏");break;
		case 2 : model.put("title", "公开");break;
		case 3 : model.put("title", "归档");break;
	    }
		return "user/home";
	}
}
	

