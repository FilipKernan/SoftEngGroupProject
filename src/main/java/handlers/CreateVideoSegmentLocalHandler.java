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
import http.CreateVideoSegmentLocalRequest;
import http.CreateVideoSegmentLocalResponse;
import model.VideoSegment;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class CreateVideoSegmentLocalHandler implements RequestHandler<CreateVideoSegmentLocalRequest, CreateVideoSegmentLocalResponse> {


    LambdaLogger logger;

    private AmazonS3 s3 = null;

    @Override
    public CreateVideoSegmentLocalResponse handleRequest(CreateVideoSegmentLocalRequest req, Context context) {
        logger = context.getLogger();
        logger.log(req.toString());

        CreateVideoSegmentLocalResponse responce;

        String id = UUID.randomUUID().toString();

        try {
            byte[] encoded = java.util.Base64.getDecoder().decode(req.base64EncodedValue);
            if (createVideoSegment(req, encoded, id)) {
                responce = new CreateVideoSegmentLocalResponse(id, 200);
            } else {
                responce = new CreateVideoSegmentLocalResponse(id, 422);
            }


        } catch (Exception e) {
            responce = new CreateVideoSegmentLocalResponse(400, "Unable to create video segment:  (" + e.getMessage() + ")");
        }


        return responce;
    }

    private boolean createVideoSegment(CreateVideoSegmentLocalRequest req, byte[] encoded, String id) {
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
            VideoSegment newVideoSegment = db.generateVideoSegment(req.character, req.transcript, id, url);
            return db.addVideoSegmentLocal(newVideoSegment);
        } catch (Exception e) {
            logger.log("could not generate new video segment: " + e.getMessage());
            return false;
        }
    }

}
