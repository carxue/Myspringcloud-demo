package com.cloud.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class RocketMQHealthIndicator implements HealthIndicator{

	@Override
	public Health health() {
		
		return null;
	}

}
