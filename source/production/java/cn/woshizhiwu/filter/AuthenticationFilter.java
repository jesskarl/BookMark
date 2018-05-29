package cn.woshizhiwu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		if(session.getAttribute("isLogin") != null) {
		  if((boolean) session.getAttribute("isLogin")) {
			  chain.doFilter(request, response);
			  return;
		  }
		}
		
		String token = (String) req.getParameter("token");
		if(token != null) {
			boolean verifyResult = true;
			if(!verifyResult) {
				res.sendRedirect("/bookmark/error/500.html");
				return;
			}
			session.setAttribute("isLogin", true);
			session.setAttribute("userEmail",req.getParameter("email"));
			chain.doFilter(request, response);
			return ;
		}
		res.sendRedirect("/bookmark/login");
	}
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
