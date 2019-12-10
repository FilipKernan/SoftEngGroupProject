package handlers;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import http.PageRedirectRequest;
import http.PageRedirectResponce;

import javax.swing.plaf.synth.Region;

public class PageRedirectHandler implements RequestHandler<PageRedirectRequest, PageRedirectResponce> {

    public LambdaLogger logger;

    @Override
    public PageRedirectResponce handleRequest(PageRedirectRequest pageRedirectRequest, Context context) {

        String url = generatePresignedURL(pageRedirectRequest.getPage());
        if (!url.isEmpty()) {
            PageRedirectResponce pageRedirectResponce = new PageRedirectResponce(url, 200);
            return pageRedirectResponce;
        } else {
            PageRedirectResponce pageRedirectResponce = new PageRedirectResponce(400, "could not create URL");
            return pageRedirectResponce;
        }
    }

    public String generatePresignedURL(String page){
        Regions region = Regions.US_EAST_2;
        String bucketName = "3733mothproject";
        String objectName = "html/" + page;
        String url = "";
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new ProfileCredentialsProvider()).build();

            java.util.Date exparationDate = new java.util.Date();
            long expTimeMillis = exparationDate.getTime();
            expTimeMillis += 1000 * 60 * 60;
            exparationDate.setTime(expTimeMillis);


            logger.log("Generating presigned URL");

            GeneratePresignedUrlRequest presignedRequest = new GeneratePresignedUrlRequest(bucketName, objectName).withMethod(HttpMethod.GET).withExpiration(exparationDate);
            url = s3Client.generatePresignedUrl(presignedRequest).toString();




        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
