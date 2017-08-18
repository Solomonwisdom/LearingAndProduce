package com.whg.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("拦截器前期处理");
		ActionContext ac=ActionContext.getContext();
		Map<String,Object> session=ac.getSession();
		if(session.get("user")==null){
//		HttpServletRequest request=ServletActionContext.getRequest();
//		String uri=request.getRequestURI();
//		session.put("uri", uri);
			return "login";
		}
		invocation.invoke();
		System.out.println("拦截器后期处理");
	
		return null;
	}
	
	

}
