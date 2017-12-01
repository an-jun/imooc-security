package com.wen.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
public class TimeFilter implements  Filter{
	/**
	 * 销毁方法
	 */
	public void destroy() {
		System.out.println("Filter destory");		
	}
	/**
	 * 执行过滤器
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			System.out.println("doFilter");
			long start =new Date().getTime();
			chain.doFilter(request, response);
			System.out.println("time fliter"+(new Date().getTime()-start));
			System.out.println("time fliter finish");
	}
	/**
	 * 创建方法
	 */
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("Filter init");
	}
}
