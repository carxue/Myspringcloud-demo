package com.cloud.service;

import java.util.concurrent.Future;

public interface HelloService {
	
	/**
	 * 同步方法
	 * @return
	 */
	public String helloService(Long id);
	
	/**
	 * 异步方法
	 * @return
	 */
	public Future<String> asynHelloService();
	
	/**
	 * 请求合并单个接口
	 * @param id
	 * @return
	 */
	public String singleHelloService(Long id);
	
}
