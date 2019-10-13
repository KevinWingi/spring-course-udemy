package com.springcourse.service.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class S3Config {

	@Value("${aws.s3.key.id}")
	private String accessKey;
	
	@Value("${aws.s3.access.key}")
	private String secretkey;
	
	@Value("${aws.s3.bucket-name}")
	private String bucketName;
	
	@Bean(name = "awsCredentialsProvider")
	public AWSCredentialsProvider getAWSCredentialsProvider() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretkey);
		return new AWSStaticCredentialsProvider(credentials);
	}
	
	@Bean(name = "bucketName")
	public String getBucketName() {
		return bucketName;
	}
}
