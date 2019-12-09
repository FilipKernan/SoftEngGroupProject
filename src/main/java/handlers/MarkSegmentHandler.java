package handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import db.VideoSegmentDAO;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;
import http.MarkSegmentRequest;
import http.MarkSegmentResponse;

public class MarkSegmentHandler implements RequestHandler<MarkSegmentRequest, MarkSegmentResponse> {

    public LambdaLogger logger;

    private AmazonS3 s3 = null;

    @Override
    public MarkSegmentResponse handleRequest(MarkSegmentRequest input, Context context){
        VideoSegmentDAO dao = new VideoSegmentDAO();
        MarkSegmentResponse response;
        String id = input.getID();
        try{
            boolean success = false;
            if(input.isMakeMarked()){
                success = dao.markVideoSegment(id);
            } else {
                success = dao.unMarkVideoSegment(id);
            }

            if(success){
                response = new MarkSegmentResponse(id, 200);
            } else {
                if(input.isMakeMarked()) {
                    response = new MarkSegmentResponse(409, "Failed to mark segment" + id);
                } else {
                    response = new MarkSegmentResponse(409, "Failed to un mark segment" + id);
                }
            }

        } catch (Exception e) {
            response = new MarkSegmentResponse(403, e.getMessage());
        }
        return response;
    }
}