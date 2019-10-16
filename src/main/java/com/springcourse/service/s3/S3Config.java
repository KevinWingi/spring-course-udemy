package com.springcourse.service.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
	@Value("${app.aws.s3.access-key}")
	private String accessKey;
	
	@Value("${app.aws.s3.secret-key}")
	private String secretKey;
	
	@Value("${app.aws.s3.bucket-name}")
	private String bucketName;

	@Bean(name = "awsS3")
	public AmazonS3 getAmazonS3() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
											.withCredentials(credentialsProvider)
											.withRegion(getRegion())
											.build();
		
		return s3;
	}
	
	@Bean(name = "awsRegion")
	public String getRegion() {
		return Region.getRegion(Regions.US_EAST_1).getName();
	}
		
	@Bean(name = "awsS3Bucket")
	public String getBucket() {
		return bucketName;
	}
}
