package com.develmagic.onemega.coinsync.config;

import static org.zalando.logbook.Conditions.contentType;
import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.header;
import static org.zalando.logbook.Conditions.requestTo;

import java.util.Arrays;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HeaderFilters;
import org.zalando.logbook.Logbook;

@Configuration
public class LogbookConfiguration {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .condition(exclude(
                        requestTo("/v2/api-docs"),
                        requestTo("/*swagger**"),
                        requestTo("/webjars/**"),
                        requestTo("/health"),
                        requestTo("/info"),
                        requestTo("/prometheus"),
                        requestTo("/admin/**"),
                        contentType("application/octet-stream"),
                        header("X-Secret", Set.of("1", "true")::contains)))
                .headerFilters(Arrays.asList(
                        HeaderFilters.replaceHeaders("authorization"::equalsIgnoreCase, "XXX"),
                        HeaderFilters.replaceHeaders("x-signature"::equalsIgnoreCase, "XXX"),
                        HeaderFilters.replaceHeaders("x-api-key"::equalsIgnoreCase, "XXX"),
                        HeaderFilters.removeHeaders("x-forwarded-for"::equalsIgnoreCase),
                        HeaderFilters.removeHeaders("x-real-ip"::equalsIgnoreCase)))
                .build();
    }

}
