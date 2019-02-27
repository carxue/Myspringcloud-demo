package com.cloud.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author wjg
 * @date 2017/12/22 15:15
 */
@Component
@WebFilter(filterName = "hystrixRequestContextServletFilter",urlPatterns = "/*")
public class HystrixRequestContextServletFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(HystrixRequestContextServletFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("过滤器开始。。。");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
       try{
           filterChain.doFilter(servletRequest,servletResponse);
       }finally {
           context.close();//context.shutdown();close其实就是调用shutdown
       }
//        logger.info("过滤器结束。。。");
    }

    @Override
    public void destroy() {

    }
}
