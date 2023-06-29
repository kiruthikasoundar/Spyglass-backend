package com.skillstorm.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
//import com.skillstorm.demo.services.UserService;

//import com.amazonaws.HttpMethod;
//import com.skillstorm.demo.services.AwsS3Service;
//import java.util.UUID;
//import java.io.IOException;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:5173","https://kiruthika-project3-spyglass.s3-website-us-east-1.amazonaws.com", "https://Kiruthika-project2-backend-elastic-env.eba-eethncyr.us-east-1.elasticbeanstalk.com"})
public class UserController {

	@Autowired
	private OAuth2AuthorizedClientService clientService;
	
//	private final AwsS3Service awsS3Service;
//	
//	
//    public UserController(AwsS3Service awsS3Service) {
//        this.awsS3Service = awsS3Service;
//    }

    @Value("${aws.bucket.name}")
    private String bucketName;
    
	@Value("${frontend-url}")
	private String frontendUrl;
	
	@GetMapping("/signin")
	public RedirectView redirectView() {
		RedirectView redirectView = new RedirectView(frontendUrl+"/navbar");
		return redirectView;
	}

	@GetMapping("/userinfo")
	@ResponseBody 
	public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User user) {
		return user.getAttributes();
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	    }
	    return ResponseEntity.ok("Logout successful");
	}


	@GetMapping("/access")
	@ResponseBody
	public String accessToken(Authentication auth) {
		if (auth instanceof OAuth2AuthenticationToken) {
			OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) auth;
			OAuth2AuthorizedClient client = clientService
					.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
			return client.getAccessToken().getTokenValue();
		}
		return "";
	}


	@GetMapping("/id")
	@ResponseBody
	public String idToken(@AuthenticationPrincipal OAuth2User user) {
		return ((DefaultOidcUser) user).getIdToken().getTokenValue();
	}
}
	