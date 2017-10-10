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
@WebFilter(filterName = "CommonFilter", urlPatterns = "/*")
public class CommonFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hrequest = (HttpServletRequest)req;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) resp);
        String basePath = req.getServletContext().getRealPath("/");
        if(!basePath.endsWith(File.separator))
            basePath += File.separator;
        basePath += "uploads";
//        判断是否已经可以下载预测数据文件
        if(new File(basePath+File.separator+"file.txt").exists()) {
            Scanner fileStatus = new Scanner(new FileReader(basePath+File.separator+"file.txt"));
            String fs = fileStatus.nextLine();
            fileStatus.close();
            hrequest.getSession().setAttribute("downloadStatus", fs);
        } else {
            hrequest.getSession().setAttribute("downloadStatus", "none");
        }
        if(!new File(basePath+File.separator+"status.txt").exists()) {
            chain.doFilter(req, resp);
            return;
        }
        Scanner status = new Scanner(new FileReader(basePath+File.separator+"status.txt"));
        String st = status.nextLine();
        status.close();
        // 等待后台进程
        if(st.equals("wait")) {
            hrequest.getSession().setAttribute("process","wait");
        } else if(st.equals("go")) {
            hrequest.getSession().setAttribute("process", "go");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
