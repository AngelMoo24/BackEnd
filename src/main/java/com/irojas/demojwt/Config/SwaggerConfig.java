package com.irojas.demojwt.Config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {    @Bean
public GroupedOpenApi openApi() {

    return GroupedOpenApi.builder()
            .group("all-apis")
            .pathsToMatch("/**")
            .addOperationCustomizer((operation, handlerMethod) ->
                    operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth")))
            .build();
}
}
