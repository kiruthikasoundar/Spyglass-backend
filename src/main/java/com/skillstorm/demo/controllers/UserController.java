package com.skillstorm.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
//import com.skillstorm.demo.services.UserService;

import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = {"http://localhost:5173","http://kiruthika-project3-spyglass.s3-website-us-east-1.amazonaws.com" })
public class UserController {

	@Autowired
	private OAuth2AuthorizedClientService clientService;
	
//	@Autowired
//    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

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
//	
//    @GetMapping("/users")
//    public ResponseEntity<List<UserDto>> getAllUsers() {
//        List<UserDto> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
//        UserDto user = userService.getUserById(id);
//        return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto) {
//        UserDto createdUser = userService.createUser(userDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @Validated @RequestBody UserDto userDto) {
//        UserDto updatedUser = userService.updateUser(id, userDto);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
}

