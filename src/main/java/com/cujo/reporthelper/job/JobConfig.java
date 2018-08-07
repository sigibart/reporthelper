package com.cujo.reporthelper.job;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class JobConfig {
 
	@Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(5);
    }
}
