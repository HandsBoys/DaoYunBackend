package com.dy.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");
        List<SecurityRequirement> list = new ArrayList<>();
        list.add(securityRequirement);
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .name("Authorization")
                                        .description("token令牌")
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .security(list)
                .info(new Info().title("到云 API")
                        .description("工程实践项目到云的OpenApi3接口。去除验证码，登录和注册。sys开头的接口是后台系统的接口，其他主要是移动端接口。")
                        .version("v0.0.1"));
    }
}
