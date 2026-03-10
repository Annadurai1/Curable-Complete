package com.curable.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.curable.util.Constant;

/**
 * 
 * @author Annaduari S
 *
 */
@Component
@Configuration
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	
        String userName = SecurityUtils.getCurrentUserLogin();
        if (userName == null ) {
        	return Optional.of(Constant.SYSTEM_ACCOUNT);
        }
        return Optional.of(userName);
    }
}
