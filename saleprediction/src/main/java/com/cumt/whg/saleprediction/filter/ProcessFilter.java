package com.cumt.whg.saleprediction.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Created by wanghaogang on 2017/6/29.
 */
@WebFilter(filterName = "ProcessFilter", urlPatterns = {"/data_*","/agentAnalyse","/dailyAnalyse"})
public class ProcessFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hrequest = (HttpServletRequest)req;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) resp);
        if(hrequest.getSession().getAttribute("END_DAY")==null&&hrequest.getAttribute("END_DAY")==null) {
            wrapper.sendRedirect("data_load.jsp");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
