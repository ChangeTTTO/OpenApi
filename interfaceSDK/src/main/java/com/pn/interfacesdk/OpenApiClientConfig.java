package com.pn.interfacesdk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties("OpenApi.Client")
@Data
@ComponentScan
public class OpenApiClientConfig {
}
