package com.skillstorm.demo.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.services.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/goals")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:5173","http://kiruthika-project3-spyglass.s3-website-us-east-1.amazonaws.com", "http://Kiruthika-project2-backend-elastic-env.eba-eethncyr.us-east-1.elasticbeanstalk.com"})
public class GoalController {

	@Autowired
    private GoalService goalService;
	
//	@Autowired
//	private AmazonS3 amazonS3;
	
    @Value("${aws.access-key}")
    private String accessKeyId;

    @Value("${aws.secret-key}")
    private String accessKeySecret;
	
    @Value("${aws.bucket.name}")
    private String bucketName;

    @GetMapping
    public List<GoalDto> getGoalsByUserId(@AuthenticationPrincipal OAuth2User user) {
    	String userId = (String) user.getAttributes().get("sub");
        return goalService.getAllGoalsByUserId(userId);     
    }

    
    @PutMapping(path = "/{id}/upload", consumes = "multipart/form-data")
	public ResponseEntity<String> uploadImage(
			  @RequestParam("image") MultipartFile image, 
			  @PathVariable Long id, 
			  @AuthenticationPrincipal OAuth2User user) 
	{
		
		if (!image.isEmpty()) {
			GoalDto goalData = goalService.getGoalById(id);
			String existingImagePath = goalData.getPictureUrl();
			String s3path;
			if (existingImagePath == null || existingImagePath.length() == 0) {
				
				String userId = (String) user.getAttributes().get("sub");
				String origFileName = image.getOriginalFilename();
				s3path = userId + "/" + origFileName;
			} else {
				s3path = existingImagePath;
			}
		
			InputStream imageInputStream;
			try {				
				imageInputStream = image.getInputStream();
			} catch (IOException e) {
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Couldn't get inputStream.\"}");
			}
						
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(image.getSize());
			
			AWSCredentials credentials = new BasicAWSCredentials(
					accessKeyId, 
					accessKeySecret
					);

			AmazonS3 s3client = AmazonS3ClientBuilder
					  .standard()
					  .withCredentials(new AWSStaticCredentialsProvider(credentials))
					  .withRegion(Regions.US_EAST_1)
					  .build();

			s3client.putObject(
					new PutObjectRequest(
						bucketName, 
						s3path,
						imageInputStream, 
						metadata));
	
			goalData.setPictureUrl(s3path);
			goalService.updateGoal(id, goalData);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Image uploaded successfully!\"}");
	    }
		
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"No image file provided.\"}");
	  }

    @PostMapping
    public ResponseEntity<GoalDto> createGoal(@AuthenticationPrincipal OAuth2User user,@Valid @RequestBody GoalDto goalDto) {
    	String userId = (String) user.getAttributes().get("sub");
    	goalDto.setUserId(userId);
    	GoalDto createdGoal = goalService.createGoal(goalDto);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

    @PutMapping("/{goalId}")
    public GoalDto updateGoal(@PathVariable Long goalId,@Valid @RequestBody GoalDto goalDto) {
    	return goalService.updateGoal(goalId, goalDto);
    }

    @DeleteMapping("/{goalId}")
    public void deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);    
    }
}
