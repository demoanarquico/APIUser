package cl.bci.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import cl.bci.user.security.CustomAuthenticationEntryPointFailureHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
    @Autowired
    private CustomAuthenticationEntryPointFailureHandler customAuthenticationEntryPointsFailureHandler;
	
	 @Override
	 public void configure(HttpSecurity http) throws Exception {
		 
		  http
         .anonymous().and()
         .authorizeRequests()
         .antMatchers("/api/user/**").authenticated()
         .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPointsFailureHandler)
         ;

	    }


}
