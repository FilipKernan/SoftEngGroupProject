//package handlers;
//
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.lambda.runtime.*;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ListObjectsV2Request;
//import com.amazonaws.services.s3.model.ListObjectsV2Result;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectInputStream;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
//import http.ReturnPlaylistsResponse;
//import model.Playlist;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class GetAllPlaylistsHandler implements RequestHandler<Object, ReturnPlaylistsResponse> {
//    public LambdaLogger logger;
//
//    // I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
//    private AmazonS3 s3 = null;
//
//    /**
//     * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
//     * be an incredible number of objects in the bucket. Ignoring this for now.
//     */
//    List<Playlist> systemConstants() throws Exception {
//        logger.log("in systemConstants");
//        if (s3 == null) {
//            logger.log("attach to S3 request");
//            s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
//            logger.log("attach to S3 succeed");
//        }
//        ArrayList<Playlist> sysConstants = new ArrayList<>();
//
//        // retrieve listing of all objects in the designated bucket
//        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
//                .withBucketName("3733christest")    // top-level bucket
//                .withPrefix("constants");       // sub-folder declarations here (i.e., a/b/c)
//
//
//        // request the s3 objects in the s3 bucket '3733christest/constants' -- change based on your bucket name
//        logger.log("process request");
//        ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
//        logger.log("process request succeeded");
//        List<S3ObjectSummary> objects = result.getObjectSummaries();
//
//        for (S3ObjectSummary os: objects) {
//            String name = os.getKey();
//            logger.log("S3 found:" + name);
//
//            // If name ends with slash it is the 'constants/' bucket itself so you skip
//            if (name.endsWith("/")) { continue; }
//
//            S3Object obj = s3.getObject("3733christest", name);
//
//            try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
//                Scanner sc = new Scanner(constantStream);
//                String val = sc.nextLine();
//                sc.close();
//
//                // just grab name *after* the slash. Note this is a SYSTEM constant
//                int postSlash = name.indexOf('/');
//                sysConstants.add(new Constant(name.substring(postSlash+1), Double.valueOf(val), true));
//            } catch (Exception e) {
//                logger.log("Unable to parse contents of " + name);
//            }
//        }
//
//        return sysConstants;
//    }
//
//
//
//    @Override
//    public ReturnPlaylistsResponse handleRequest(Object input, Context context){
//        return null;
//    }
//}