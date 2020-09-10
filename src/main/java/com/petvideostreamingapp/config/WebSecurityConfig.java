package com.petvideostreamingapp.config;

import com.petvideostreamingapp.model.User;
import com.petvideostreamingapp.repo.UserDetailsRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@EnableWebSecurity
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/login**", "/error**", "/js/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
		// @formatter:on
	}

	@Bean
	public PrincipalExtractor principalExtractor(UserDetailsRepository userDetailsRepo) {
		return map -> {
			String id = (String) map.get("sub");

			User user = userDetailsRepo.findById(id).orElseGet(() -> {
				User newUser = new User();

				newUser.setId(id);
				newUser.setName((String) map.get("name"));
				newUser.setEmail((String) map.get("email"));
				newUser.setGender((String) map.get("gender"));
				newUser.setLocale((String) map.get("locale"));
				newUser.setUserpic((String) map.get("picture"));

				return newUser;
			});

			user.setLastVisit(LocalDateTime.now());

			return userDetailsRepo.save(user);
		};
	}

}