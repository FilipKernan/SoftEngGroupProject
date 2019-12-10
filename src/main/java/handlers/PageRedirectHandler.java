package handlers;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import http.PageRedirectRequest;
import http.PageRedirectResponce;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import javax.swing.plaf.synth.Region;
import java.net.URL;

public class PageRedirectHandler implements RequestHandler<PageRedirectRequest, PageRedirectResponce> {

    public LambdaLogger logger;

    @Override
    public PageRedirectResponce handleRequest(PageRedirectRequest pageRedirectRequest, Context context) {
        logger = context.getLogger();
        try {
            URL url = generatePresignedURL(pageRedirectRequest.getPage());
            if (!url.toString().isEmpty()) {
                PageRedirectResponce pageRedirectResponce = new PageRedirectResponce(url, 200);
                return pageRedirectResponce;
            } else {
                PageRedirectResponce pageRedirectResponce = new PageRedirectResponce(400, "could not create URL");
                return pageRedirectResponce;
            }
        } catch (Exception e) {
            logger.log("a problem happened it geneatating the url");
            logger.log(e.getMessage());
        }
        return null;

    }

    public URL generatePresignedURL(String page) throws Exception{

        Regions region = Regions.US_EAST_2;
        String bucketName = "3733mothproject";
        String objectName = "html/" + page;
        URL url = null;
        try {
            AWSCredentialsProvider awsCredentialsProvider = new DefaultAWSCredentialsProviderChain();

            AmazonS3 s3Client =AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(awsCredentialsProvider)
                    .build();
;

            java.util.Date exparationDate = new java.util.Date();
            long expTimeMillis = exparationDate.getTime();
            expTimeMillis += 1000 * 60 * 60;
            exparationDate.setTime(expTimeMillis);


            logger.log("Generating presigned URL");

            GeneratePresignedUrlRequest presignedRequest = new GeneratePresignedUrlRequest(bucketName, objectName).withMethod(HttpMethod.GET).withExpiration(exparationDate);
            url = s3Client.generatePresignedUrl(presignedRequest);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
