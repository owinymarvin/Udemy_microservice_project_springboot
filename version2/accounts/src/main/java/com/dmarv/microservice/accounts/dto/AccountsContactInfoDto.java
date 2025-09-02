package com.dmarv.microservice.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// Record classes make the initialized data final, and cant be
// changed at runtime. Hence, perfect for these values that won't
// change during runtime.
@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String,
        String> contactDetails, List<String> onCallSupport) {
}
