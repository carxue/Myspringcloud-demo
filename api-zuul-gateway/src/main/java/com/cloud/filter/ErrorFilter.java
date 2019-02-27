package com.cloud.filter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author 43874 实现路由之前的权利拦截等功能
 *
 */
@Component
public class ErrorFilter extends ZuulFilter {

	private final Logger logger = Logger.getLogger(ErrorFilter.class);

	@Override
	public Object run() {
		logger.info("this is a error filter,it will throw a RuntimeException");
		RequestContext context = RequestContext.getCurrentContext();
		Throwable e = context.getThrowable();
		context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		context.set("error.exception", e.getCause());
		context.set("error.message", e.getMessage());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;// 判断该过滤器是否需要被执行
	}

	@Override
	public int filterOrder() {
		return 10;// 过滤器的执行顺序，如果有多个则根据返回值确立执行顺序
	}

	@Override
	public String filterType() {// "pre" "route" "post" "error"
		return "error";// 确定在哪个生命周期中执行
	}

}
