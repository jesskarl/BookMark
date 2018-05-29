package cn.woshizhiwu.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.woshizhiwu.entity.Url;
import cn.woshizhiwu.form.UrlForm;
import cn.woshizhiwu.service.UrlService;
import cn.woshizhiwu.tools.CurrentUser;


@Controller
@RequestMapping("/user")
public class UrlController {
	@Inject UrlService urlService;
	
	@RequestMapping(value = "/url/{urlId:\\d+}", method = RequestMethod.GET)
	public String viewUrl(Map<String,Object> model , @PathVariable("urlId") long id) {
		Url url = urlService.getUrl(id);
		if (url != null) {
			model.put("url", url);
			return "user/redirect";
		}
		return "error/404";
	}
	
	@RequestMapping(value = "/addurl", method = RequestMethod.GET)
	public String addUrl(Map<String,Object> model) {
		model.put("urlForm",new UrlForm());
		return "user/addurl";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/addurl", method = RequestMethod.POST)
	public ModelAndView addUrl(UrlForm form,Map<String,Object> model,
			HttpServletRequest req, HttpServletResponse res) throws MalformedURLException{
		CurrentUser currentUser = new CurrentUser(req,res);
		long uID = currentUser.getId();
		Url url = new Url();
		url.setUserId(uID);
		url.setTitle(form.getTitle());
		for(String t : form.getTags().split(" ")) {    
			url.addTag(t);
		}
		try {
		url.setUrl(new URL(form.getUrl()));
		}catch(MalformedURLException e) {
				url.setUrl(new URL("http://" + form.getUrl()));
		}finally{
			this.urlService.saveUrl(url,form.getTags());
			return new ModelAndView(new RedirectView("/user/home",true));
		}
		
	}

	@RequestMapping(value = "/urlsetstatus", method = RequestMethod.GET)
	public ModelAndView setUrlStatus(Map<String, Object> model, 
			                     HttpServletRequest req,HttpServletResponse res,
			                    @RequestParam(value = "urlId", required = true) long id,
                           @RequestParam(value = "category", required = true) int category) {
		  if(category > 3 || category < 0) {
			  category = 0;
		    }
		  Url url = this.urlService.getUrl(id);
			List<Integer> urlcat = url.getCategory();
		  if(category == 3) {
			  url.setArchive(!url.getArchive());
		  }
			else if(urlcat.contains(category)) {
				urlcat.remove((Object)category);
			}else {
				urlcat.add(category);
			}
			
		return new ModelAndView(new RedirectView("/user/home",true),model);
	}
	
	@RequestMapping(value = "/urldelete", method = RequestMethod.GET)
	public ModelAndView deleteUrl(Map<String, Object> model, 
			                     HttpServletRequest req,HttpServletResponse res,
			                    @RequestParam(value = "urlId", required = true) int id) {
		CurrentUser currentUser = new CurrentUser(req,res);
		long uID = currentUser.getId();
		if(id != uID) {
		}else {
			this.urlService.delete(uID,id);
		}
		return new ModelAndView(new RedirectView("/user/home",true));
		
	}
	
}
