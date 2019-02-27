package com.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author 43874 实现路由之前的权利拦截等功能
 *
 */
public class AccessFilter extends ZuulFilter {

	private final Logger logger = Logger.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info("send: " + request.getMethod() + " request to: " + request.getRequestURL().toString());

		Object accessToken = request.getParameter("accessToken");
		if (accessToken == null) {
			logger.info("access token is empty");
			ctx.setSendZuulResponse(false);//设置没有token则不对请求路由
			ctx.setResponseStatusCode(401);
			return null;
		}
		logger.info("access token ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;//判断该过滤器是否需要被执行
	}

	@Override
	public int filterOrder() {
		return 0;// 过滤器的执行顺序，如果有多个则根据返回值确立执行顺序
	}

	@Override
	public String filterType() {//"pre" "route" "post" "error"
		return "pre";//确定在哪个生命周期中执行
	}

}
