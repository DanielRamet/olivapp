package com.xapaya.olivapp.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("app.auth")
@Configuration
public class AppAuthProperties {
    String jwtCookieName;
    String jwtSecret;
    Long jwtExpirationMs;
}
