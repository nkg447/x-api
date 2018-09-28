package com.xapi;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    static String api_key = "api key here";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("authenticating");
        try{
            if(req.getParameter("api_key").equals(api_key)){
                System.out.println("authenticated");
                chain.doFilter(req, resp);
            }
            else{
                resp.getWriter().write("api_key invalid");
            }
        }catch(NullPointerException e){
            resp.getWriter().write("no api_key found");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
