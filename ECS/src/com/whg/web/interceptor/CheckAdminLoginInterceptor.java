package com.whg.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.whg.web.common.Constant;
import com.whg.web.entity.User;

import java.util.Map;

/**
 * Created by wanghaogang on 2017/7/5.
 */
public class CheckAdminLoginInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext ac=ActionContext.getContext();
        Map<String,Object> session=ac.getSession();
        User user = (User) session.get("user");
        if(user == null) {
            return "login";
        }
        else if(user.getUserIntegral() != Constant.ADMIN) {
            return "ban";
        }
        return null;
    }
}
