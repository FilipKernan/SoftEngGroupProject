package handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import db.VideoSegmentDAO;
import http.CreateVideoSegmentRequest;
import http.CreateVideoSegmentResponse;
import model.VideoSegment;

import java.io.ByteArrayInputStream;
import java.util.Dictionary;
import java.util.UUID;

public class CreateVideoSegmentHandler implements RequestHandler<CreateVideoSegmentRequest, CreateVideoSegmentResponse> {


    LambdaLogger logger;

    private AmazonS3 s3 = null;

    @Override
    public CreateVideoSegmentResponse handleRequest(CreateVideoSegmentRequest req, Context context) {
        logger = context.getLogger();
        logger.log(req.toString());

        CreateVideoSegmentResponse responce;

        String id = UUID.randomUUID().toString();

        try {
            byte[] encoded = null;
            if (req.isLocal()) {
                encoded = java.util.Base64.getDecoder().decode(req.getBase64Encodedvalue());
            }
            if (!videoExistesInDataBase(req).isEmpty()){
                id = videoExistesInDataBase(req);
                responce = new CreateVideoSegmentResponse(id, 200);
            } else {
                if (createVideoSegment(req, encoded, id)) {
                    responce = new CreateVideoSegmentResponse(id, 200);
                } else {
                    responce = new CreateVideoSegmentResponse(id, 422);
                }
            }



        } catch (Exception e) {
            responce = new CreateVideoSegmentResponse(400, "Unable to create video segment:  (" + e.getMessage() + ")");
        }


        return responce;
    }

    private String videoExistesInDataBase(CreateVideoSegmentRequest req) {

        VideoSegmentDAO db = new VideoSegmentDAO();
        try {
            return db.findURL(req.getTpsURL());
        } catch (Exception e) {
            return "";
        }

    }

    private boolean createVideoSegment(CreateVideoSegmentRequest req, byte[] encoded, String id) {
        if(req.isLocal()){
            if (logger != null) { logger.log("in create Video Segment"); }

            if (s3 == null) {
                logger.log("attach to S3 request");
                s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
                logger.log("attach to S3 succeed");
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(encoded);
            ObjectMetadata omd = new ObjectMetadata();
            omd.setContentLength(encoded.length);

            PutObjectResult res = s3.putObject(new PutObjectRequest("3733mothproject", "videoSegments/" + id + ".ogg", bais, omd));

            String url = "https://3733mothproject.s3.us-east-2.amazonaws.com/videoSegments/" + id + ".ogg" ;
            VideoSegmentDAO db = new VideoSegmentDAO();
            try {
                VideoSegment newVideoSegment = db.generateVideoSegment(req.getCharacter(), req.getTranscript(), id, url);
                return db.addVideoSegment(newVideoSegment, 1); //1 means it is local, 0 means it isn't
            } catch (Exception e) {
                logger.log("could not generate new video segment: " + e.getMessage());
                return false;
            }

        } else {
            VideoSegmentDAO db = new VideoSegmentDAO();
            try {
                VideoSegment newVideoSegment = db.generateVideoSegment(req.getCharacter(), req.getTranscript(), id, req.tpsURL);
                // check if it already exists in the database
                // if so set the id to the one in the database
                return db.addVideoSegment(newVideoSegment, 0); //1 means it is local, 0 means it isn't
            } catch (Exception e) {

                logger.log("could not generate new video segment: " + e.getMessage());
                return false;
            }

        }

    }

}
