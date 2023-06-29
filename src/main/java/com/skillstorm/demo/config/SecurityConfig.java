package com.skillstorm.demo.config;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

	@Value("${frontend-url}")
	private String frontendUrl;
	
    @Value("${aws.bucket.name}")
    private String bucketName;
    
    @Value("${aws.region}")
    private String s3RegionName;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.oauth2Login()
				.and()
				.logout(logout -> logout.permitAll()
		                .logoutSuccessHandler((request, response, authentication) -> {
		                    response.setStatus(HttpServletResponse.SC_OK);
		                }));
		
		http.cors().configurationSource(request -> {
			CorsConfiguration corsConfig = new CorsConfiguration();
			corsConfig.setAllowedOrigins(Arrays.asList(frontendUrl,"http://kiruthika-project3-spyglass.s3-website-us-east-1.amazonaws.com","http://Kiruthika-project2-backend-elastic-env.eba-eethncyr.us-east-1.elasticbeanstalk.com", "https://accounts.google.com"));
//			corsConfig.addAllowedOrigin(frontendUrl);
//			corsConfig.addAllowedOrigin("http://kiruthika-project3-spyglass.s3-website-us-east-1.amazonaws.com*");
//			corsConfig.addAllowedOrigin("http://Kiruthika-project2-backend-elastic-env.eba-eethncyr.us-east-1.elasticbeanstalk.com*");
			corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type","Origin", "X-Auth-Token"));
			corsConfig.setAllowCredentials(true);
			corsConfig.setMaxAge(3600L);
//			corsConfig.addAllowedOrigin("https://accounts.google.com");
			corsConfig.addAllowedHeader("Authorization");
			corsConfig.addExposedHeader("Authorization"); 
			corsConfig.addAllowedHeader("*");
			
            corsConfig.addAllowedOrigin("https://" + bucketName + ".s3.amazonaws.com");
            corsConfig.addAllowedOrigin("https://" + bucketName + ".s3." + s3RegionName + ".amazonaws.com");

			
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", corsConfig);
			
			return corsConfig;
		});
	return http.build();
	}
}
