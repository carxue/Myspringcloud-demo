package com.cloud.filter;

import javax.servlet.http.HttpServletRequest;
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
public class ThrowExceptionFilter extends ZuulFilter {

	private final Logger logger = Logger.getLogger(ThrowExceptionFilter.class);

	@Override
	public Object run() {
		logger.info("this is a pre filter,it will throw a RuntimeException");
		RequestContext context = RequestContext.getCurrentContext();
//		try {
			dosomething();
//		} catch (Exception ex) {
//			context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			context.set("error.exception", ex);
//			context.set("error.message", ex.getMessage());
//		}

		return null;
	}

	private void dosomething() {
		throw new RuntimeException("exit some errors...");
	}

	@Override
	public boolean shouldFilter() {
		return true;// 判断该过滤器是否需要被执行
	}

	@Override
	public int filterOrder() {
		return 0;// 过滤器的执行顺序，如果有多个则根据返回值确立执行顺序
	}

	@Override
	public String filterType() {// "pre" "route" "post" "error"
		return "post";// 确定在哪个生命周期中执行
	}

}
