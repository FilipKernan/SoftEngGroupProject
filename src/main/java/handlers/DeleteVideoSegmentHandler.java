package handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import db.PlaylistDAO;
import db.VideoSegmentDAO;
import http.DeletePlaylistRequest;
import http.DeletePlaylistResponse;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;

public class DeleteVideoSegmentHandler implements RequestHandler<DeleteVideoSegmentRequest, DeleteVideoSegmentResponse> {

    public LambdaLogger logger;

    private AmazonS3 s3 = null;

    boolean deleteVideoSegment(String id) throws Exception {
        VideoSegmentDAO dao = new VideoSegmentDAO();

        return dao.deleteVideoSegment(id);
    }

    @Override
    public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest input, Context context){
        logger = context.getLogger();
        logger.log(input.toString());
        DeleteVideoSegmentResponse response;
        String id = input.getID();
        try{

            if (logger != null) { logger.log("in createSystemConstant"); }

            if (s3 == null) {
                logger.log("attach to S3 request");
                s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
                logger.log("attach to S3 succeed");
            }
            s3.deleteObject("3733mothproject", "videoSegments/" + input.getID() + ".ogg");


            boolean success = deleteVideoSegment(id);

            if(success){
                response = new DeleteVideoSegmentResponse(id, 200);
            } else {
                response = new DeleteVideoSegmentResponse(id, 409);
            }

        } catch (Exception e) {
            response = new DeleteVideoSegmentResponse(403, e.getMessage());
        }
        return response;
    }
}