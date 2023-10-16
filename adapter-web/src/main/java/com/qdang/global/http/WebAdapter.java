package com.qdang.global.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@ResponseBody
@RequestMapping
public @interface WebAdapter {

	@AliasFor(annotation = Component.class)
	String value() default "";

	@AliasFor(annotation = RequestMapping.class, attribute = "path")
	String path() default "";
}
