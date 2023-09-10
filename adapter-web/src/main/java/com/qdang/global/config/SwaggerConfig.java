package com.qdang.global.config;

import com.qdang.global.argument.LoginUser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("API Document")
				.description("Q-Dang API Document")
				.version("v1.0.0");

		SecurityScheme securityScheme = new SecurityScheme()
				.type(SecurityScheme.Type.HTTP)
				.in(SecurityScheme.In.HEADER)
				.name("Authorization")
				.scheme("bearer")
				.bearerFormat("JWT");
		SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Token");

		return new OpenAPI()
				.addServersItem(new Server().url("/").description("Current Server url"))
				.addServersItem(new Server().url("https://api.q-dang.com").description("Production Server url"))
				.addServersItem(new Server().url("http://localhost:8080").description("Local Server url"))
				.components(new Components().addSecuritySchemes("Bearer Token", securityScheme))
				.security(Arrays.asList(securityRequirement))
				.info(info);
	}

	static {
		SpringDocUtils.getConfig().addAnnotationsToIgnore(LoginUser.class);
	}
}