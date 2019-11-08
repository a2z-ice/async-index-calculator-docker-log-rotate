package com.example.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfiguration {
	
	
	private static final int MAX_POOL_SIZE = 20;
	private static final int CORE_POLL_SIZE = 15;
	private static final int QUEUE_CAPACITY = 15;
	
	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(MAX_POOL_SIZE);
		executor.setCorePoolSize(CORE_POLL_SIZE);
		executor.setQueueCapacity(QUEUE_CAPACITY);
		executor.setThreadNamePrefix("AsynchThread-");
		executor.initialize();
		return executor;
	}
}
