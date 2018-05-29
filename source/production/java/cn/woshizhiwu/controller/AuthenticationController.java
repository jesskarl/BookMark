package cn.woshizhiwu.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import cn.woshizhiwu.entity.User;
import cn.woshizhiwu.form.LoginForm;
import cn.woshizhiwu.form.RegisterForm;
import cn.woshizhiwu.service.AuthenticationService;
import cn.woshizhiwu.tools.CurrentUser;

@Controller
public class AuthenticationController {
	
	@Inject AuthenticationService authenticationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Map<String,Object> model, 
			HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(new CurrentUser(req,res).isLogin()) {
			res.sendRedirect("/bookmark/user/home");
		}
		model.put("loginForm", new LoginForm());
		model.put("info",req.getParameter("info"));
		return "authentication/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(LoginForm form, Map<String,Object> model, 
			HttpServletRequest req, HttpServletResponse res){
		if(this.authenticationService.
				checkLoginInfo(form.getEmail(), form.getPassword())) {
			//req.getSession().setAttribute("isLogin", true);
			String token = UUID.randomUUID().toString();
			model.put("token", token);
			model.put("email", form.getEmail());
			return new ModelAndView(new RedirectView("user/home",true),model);
		}
		model.put("info", "用户名或密码错误");
		return new ModelAndView(new RedirectView("/login",true), model);
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public View login(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session != null) 
			session.invalidate();
		return new RedirectView("/index",true);
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Map<String,Object> model,
			HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(new CurrentUser(req,res).isLogin()) {
			res.sendRedirect("/bookmark/user/home");
		}
		model.put("registerForm", new RegisterForm());
		model.put("info",req.getParameter("info"));
		return "authentication/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register( Map<String,Object> model, 
			@Valid RegisterForm form, Errors errors){
		
		if(errors.hasErrors()) {
			return new ModelAndView("authentication/register");
		}			
		  User user = new User();
			user.setUsername(form.getUsername());
			user.setPassword(form.getPassword());
			user.setEmail(form.getEmail());
			
			if(form.getPassword().equals(form.getPassword2())) {
				if(this.authenticationService.saveUser(user)) {
					try {
						 if(this.authenticationService.saveUser(user)) {
						  model.put("info", "注册成功");
						  return new ModelAndView(new RedirectView("/login",true), model);
						  }
						  
						}catch(ConstraintViolationException e) {
							model.put("validationErrors", e.getConstraintViolations());
							return new ModelAndView("authentication/register");
						}
					model.put("info", "注册成功");
				  return new ModelAndView(new RedirectView("/login",true), model);
				}
				else {
					model.put("info", "邮箱已被注册");
					return new ModelAndView("authentication/register");
				}
			}
			else{model.put("info", "两次密码输入不一致");
			return new ModelAndView("authentication/register");
				}
			
			
	}

}
