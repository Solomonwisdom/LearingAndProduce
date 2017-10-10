package com.cumt.whg.saleprediction.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by wanghaogang on 2017/6/28.
 */
@WebFilter(filterName = "DataFilter", urlPatterns = "/dataSummary")
public class DataFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hrequest = (HttpServletRequest)req;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) resp);
        String basePath = req.getServletContext().getRealPath("/");
        basePath += File.separator + "uploads";
        if(!new File(basePath+File.separator+"situations.txt").exists()) {
            hrequest.getSession().setAttribute("process","wait");
            wrapper.sendRedirect("data_load.jsp");
            chain.doFilter(req, resp);
            return;
        }
        Scanner situations = new Scanner(new FileReader(basePath+File.separator+"situations.txt"));
        int maxDay;
        int agentNumber;
        int validPair;
        int invalidPair;
        int[] parts = new int[5];
        if(!situations.hasNextInt()) {
            hrequest.getSession().setAttribute("process","wait");
            wrapper.sendRedirect("data_load.jsp");
            chain.doFilter(req, resp);
            return;
        }
        maxDay = situations.nextInt();
        if(!situations.hasNextInt()) {
            hrequest.getSession().setAttribute("process","wait");
            wrapper.sendRedirect("data_load.jsp");
            chain.doFilter(req, resp);
            return;
        }
        agentNumber = situations.nextInt();
        if(!situations.hasNextInt()) {
            hrequest.getSession().setAttribute("process","wait");
            wrapper.sendRedirect("data_load.jsp");
            chain.doFilter(req, resp);
            return;
        }
        validPair = situations.nextInt();
        if(!situations.hasNextInt()) {
            hrequest.getSession().setAttribute("process","wait");
            wrapper.sendRedirect("data_load.jsp");
            chain.doFilter(req, resp);
            return;
        }
        invalidPair = situations.nextInt();
        if(!situations.hasNextInt()) {
            hrequest.getSession().setAttribute("process","wait");
            wrapper.sendRedirect("data_load.jsp");
            chain.doFilter(req, resp);
            return;
        }
        for(int i=0;i<5;i++)
            parts[i] = situations.nextInt();
        hrequest.setAttribute("size", maxDay);
        hrequest.setAttribute("agentNum", agentNumber);
        hrequest.setAttribute("validate", validPair);
        hrequest.setAttribute("invalidate", invalidPair);
        for(int i=0;i<5;i++)
            hrequest.setAttribute("part"+i, parts[i]);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
