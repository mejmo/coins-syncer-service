package com.develmagic.onemega.coinsync.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.develmagic.onemega.coinsync.client")
public class FeignConfig {
}
