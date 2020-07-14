package com.example.webstroitelspringboot.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class YandexService {
    private AmazonS3 s3client;

    //  @Value("${amazon.s3.region}")
    private final String region = "ru-central1";
    //   @Value("${amazon.s3.endpoint}")
    private final String endpoint = "storage.yandexcloud.net";
    //   @Value("${amazon.s3.bucket}")
    private final String bucketName = "default";
    //    @Value("${amazon.aws.access-key-id}")
    private final String accessKey = "nWFTcG0bWEsAB2ZtGsny";
    //   @Value("${amazon.aws.access-key-secret}")
    private final String secretKey = "JQ9iLtuAde8mq9rDhJ4JIxI7fvaSNeQEptlyaG6I";


    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(endpoint, region))
                .build();
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }

    public List<String> listFiles() {
        ListObjectsRequest listObjectsRequest =
                new ListObjectsRequest()
                        .withBucketName(bucketName)
                        .withPrefix("/");

        List<String> keys = new ArrayList<>();
        ObjectListing objects = s3client.listObjects(listObjectsRequest);
        while (true) {
            List<S3ObjectSummary> summaries = objects.getObjectSummaries();
            if (summaries.size() < 1) {
                break;
            }
            for (S3ObjectSummary item : summaries) {
                if (!item.getKey().endsWith("/"))
                    keys.add(item.getKey());
            }

            objects = s3client.listNextBatchOfObjects(objects);
        }
        return keys;
    }
}
