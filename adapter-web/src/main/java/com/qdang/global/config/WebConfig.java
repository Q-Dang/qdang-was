package com.qdang.global.config;

import com.qdang.global.pathmatch.V1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("https://api.q-dang.com", "http://api.q-dang.com")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer
				.setUseTrailingSlashMatch(true)
				.addPathPrefix("/v1", HandlerTypePredicate.forAnnotation(V1.class))
				.setPathMatcher(new AntPathMatcher())
				.setUrlPathHelper(new UrlPathHelper())
		;
	}

	@Bean
	public MessageSource validationMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/validation");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validationMessageSource());
		return bean;
	}
}
