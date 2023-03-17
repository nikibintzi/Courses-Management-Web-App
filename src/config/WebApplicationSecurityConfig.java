package springbootthymeleaf.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private DataSource dataSource;
    

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() 
		/*
		 * authorizeRequests returns an ExpressionInterceptUrlRegistry that 
		 * allows restricting access to uris based on 
		 * RequestMatcher implementations
		 */
		.antMatchers("/*").hasAnyRole("USER", "ADMIN") // match specific apache ant reg exprs for urls and specify rights
		.antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		/*
		 * returns the http security object and allows to compose 
		 * multiple chains of configuration method calls
		 */
		.and() 
		.formLogin(); // specifically this method tells spring to generate a default login page
	
		// .loginPage(...).loginProcessingUrl(...)
	}


    
	
	
	
		
}


