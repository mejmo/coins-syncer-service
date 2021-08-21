package com.develmagic.onemega.coinsync.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
class RedisCacheConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(@Value("${redis.hostname}") String redisHostname) {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHostname, 6379));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("cmcCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(48)))
                .withCacheConfiguration("krakenCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(48)))
                .withCacheConfiguration("binanceCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(48)));
    }

}