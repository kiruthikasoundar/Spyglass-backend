package com.skillstorm.demo.services;


import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
//import org.springframework.stereotype.Service;
//import com.amazonaws.HttpMethod;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.UUID;

@Service
public class AwsS3Service {

    private final AmazonS3 amazonS3;


    public AwsS3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

//    public String generatePreSignedUrl(String userId, String bucketName, HttpMethod httpMethod, int validityMinutes) {
//        String fileName = "image";
//        String filePath = userId + "/" + fileName;
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.MINUTE, validityMinutes);
//
//        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, filePath, httpMethod);
//        request.setExpiration(calendar.getTime());
//
//        return amazonS3.generatePresignedUrl(request).toString();
//    }
//
//    public String generatePublicImageUrl(String bucketName, String filePath) {
//        Date expiration = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
//
//        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, filePath, HttpMethod.GET);
//        request.setExpiration(expiration);
//
//        URL publicImageUrl = amazonS3.generatePresignedUrl(request);
//        return publicImageUrl.toString();
//    }
//
//    public void uploadImageToS3(String imageUrl, InputStream imageInputStream) throws IOException {
//        URL url = new URL(imageUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("PUT");
//        connection.setDoOutput(true);
//
//        try (InputStream inputStream = imageInputStream) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                connection.getOutputStream().write(buffer, 0, bytesRead);
//            }
//        }
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            System.out.println("Image uploaded successfully.");
//        } else {
//            System.out.println("Failed to upload image. Response Code: " + responseCode);
//        }
//
//        connection.disconnect();
//    }

}
