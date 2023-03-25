package cl.bci.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)  
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .anonymous().and()
      .authorizeRequests().antMatchers("/").permitAll().and()
      .authorizeRequests().antMatchers("/h2-console/**").permitAll()
      .anyRequest().authenticated();

      http.csrf().disable();
      http.headers().frameOptions().disable();
	}

}
