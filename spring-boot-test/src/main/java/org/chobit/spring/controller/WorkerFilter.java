package org.chobit.spring.controller;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WorkerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("++++++++++++++++++++++++++++>>>> Filter");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("X-CHOBIT-APP", "chobit-header");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
