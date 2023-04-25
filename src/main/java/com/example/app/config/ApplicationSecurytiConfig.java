package com.example.app.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.app.jwt.JwtTokenFilter;
import com.example.app.repository.UserRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = false,
		securedEnabled = false,
		jsr250Enabled = true //cho phep sử dụng chú thích @RolesAllowed trong mã API 
								//để duoc ủy quyền cấp phương thức
)
public class ApplicationSecurytiConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	private LogoutHandler logoutHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
            username -> userRepo.findByUsername(username)
                .orElseThrow(
                    () -> new UsernameNotFoundException("User " + username + " not found.")));
        
    }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//show message
		http.exceptionHandling().authenticationEntryPoint(
					(request, response, ex) ->{
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						ex.getMessage());
					}
				);
		
		
		http.authorizeRequests()
			.antMatchers("/", "/api/login/**", "/api/logout/**", "/api/register/**", "/api/token/refresh/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/new/**").permitAll()
			//.antMatchers(HttpMethod.POST, "/api/new/search").permitAll()
			.anyRequest().authenticated();
		
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.logout()
			.logoutUrl("/api/logout")
			.addLogoutHandler(logoutHandler)
			.logoutSuccessHandler(
					(request, response, authentication) ->
					SecurityContextHolder.clearContext()
			);
	}
}
