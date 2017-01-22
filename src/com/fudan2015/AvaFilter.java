package com.fudan2015;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;


public class AvaFilter extends StrutsPrepareAndExecuteFilter 
{
  public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) req;  
        String URI = request.getRequestURI(); 
        // 此处的值要和 web.xml 中fck配置的路径开头一致 -->
        String[] uriArray = URI.split("/avatarupload"); 
        int arrayLen = uriArray.length; 
        if (arrayLen >= 2) { 
            chain.doFilter(req, res);  
        }else { 
            super.doFilter(req, res, chain);  
        } 
    }  
}