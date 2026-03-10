package com.curable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.curable.util.Constant;

@Configuration
public class ThymeleafConfig {
	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix(Constant.TEMPLATES_SOURCE_PATH);
		emailTemplateResolver.setTemplateMode(Constant.TEMPLATES_MODE);
		emailTemplateResolver.setSuffix(Constant.TEMPLATES_SUFFIX);
		emailTemplateResolver.setTemplateMode(Constant.XHTML);
		emailTemplateResolver.setCharacterEncoding(Constant.UTF_8);
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}
}
