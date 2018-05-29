package cn.woshizhiwu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import cn.woshizhiwu.tools.CurrentUser;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController
{
    @RequestMapping("/")
    public View toIndex(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res){
    	    CurrentUser currentUser = new CurrentUser(req,res);
    	    if(currentUser.isLogin()) {
    	       	return new RedirectView("/user/home", true);
    	    }
        model.put("indexUrl", "index");
        return new RedirectView("/index", true);
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model,
    		HttpServletRequest req, HttpServletResponse res) throws IOException{
    	  CurrentUser currentUser = new CurrentUser(req,res);
			model.put("isLogin", currentUser.isLogin());
			model.put("user", currentUser.getUser());
    	  return "index";
        }
    
}
