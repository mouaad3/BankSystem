package com.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springsecurity.service.CustomerService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomerService customerService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SpringSecurityConfig(CustomerService customerService, PasswordEncoder passwordEncoder) {
		this.customerService = customerService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	 * provider.setUserDetailsService(customerService);
	 * provider.setPasswordEncoder(passwordEncoder); return provider; }
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.authenticationProvider(authenticationProvider()); }
	 */
	  @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customerService).passwordEncoder(passwordEncoder);
	    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/registration/**","/js/**","/css/**","/img/**").permitAll()
		.antMatchers("/api/v1/bank/customer").permitAll()         
		.antMatchers("/registration").permitAll()         
		.antMatchers("/api/v1/bank/customer/edit/**","/api/v1/bank/update/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TRAINEE")            
		.antMatchers("/api/v1/bank/customer/delete/**","/api/v1/bank/customers").hasAuthority("ROLE_ADMIN") 
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/api/v1/bank/customer")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}

}
