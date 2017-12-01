package com.wen.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wen.security.browser.support.SimpleResponse;
import com.wen.security.core.properties.SecurityProperties;

@RestController
public class BrowserSecurityController {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private RequestCache requestCache=new HttpSessionRequestCache();
	
	private RedirectStrategy  redirectStrategy=new DefaultRedirectStrategy();
	@Autowired
	private SecurityProperties securityProperties;
	/**
	 * 当需要身份认证的时候跳转到这里
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) {
		logger.info((securityProperties.getBrowser().getLoginPage()));
		SavedRequest savedRequest= requestCache.getRequest(request, response);
		if(savedRequest!=null) {
			String targetUlr=savedRequest.getRedirectUrl();
			logger.info("引发跳转url"+targetUlr);
			if(StringUtils.endsWithIgnoreCase(targetUlr, ".html")) {
				try {
					System.out.println(securityProperties.getBrowser().getLoginPage());
					redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户进行身份认证");
	}
	@RequestMapping("/browser/test")
	public String testBrowser() {
		return "haha";
	}
}
