package org.rakotulkki;

import org.rakotulkki.filter.CsrfHeaderFilter;
import org.rakotulkki.services.TherapistUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * @author jkuittin
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final static Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	private TherapistUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http
			.httpBasic()
			.and()
			.authorizeRequests()
			  .antMatchers(
				"/index.html",
				"/login.html",
				"/views/landing.html",
				"/",
				"/css/**/*.css",
				"/js/**/*.js",
				"/js/**/*.css")
			  .permitAll()
			  .anyRequest().authenticated()
			.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository());
		//@formatter:on
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}

