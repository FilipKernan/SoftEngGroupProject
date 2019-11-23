package handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import http.ReturnPlaylistsResponse;

public class GetLocalVideoSegmentsHandler implements RequestHandler<Object, ReturnPlaylistsResponse> {

    @Override
    public ReturnPlaylistsResponse handleRequest(Object input, Context context){
        return null;
    }
}