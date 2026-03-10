package com.curable;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@ComponentScan(basePackages = {"com.curable"})
@EnableSwagger2
@EnableJpaRepositories(basePackages = "com.curable.repository")
@RequestMapping("/api")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class CurableDisease {
 
    public static void main(String[] args) {
        SpringApplication.run(CurableDisease.class, args);
    }

	@Bean
    public Docket productApi() {
       return new Docket(DocumentationType.SWAGGER_2).select()
          .apis(RequestHandlerSelectors.basePackage("com.curable")).build();
    }
    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
