package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.VideoSegmentDAO;
import http.LocalVideoSegmentResponse;
import http.LocalVideoSegmentsRequest;
import model.VideoSegment;

import java.util.List;

public class GetLocalVideosSegmentsHandler implements RequestHandler<LocalVideoSegmentsRequest, LocalVideoSegmentResponse> {
    public LambdaLogger logger;

    @Override
    public LocalVideoSegmentResponse handleRequest(LocalVideoSegmentsRequest localVideoSegmentsRequest, Context context) {

        logger = context.getLogger();

        logger.log("Handling localVideoSegment lambda");

        LocalVideoSegmentResponse response;

        try {
            List<VideoSegment> list = getVideoSegments();
            response = new LocalVideoSegmentResponse(list, 200);
        } catch (Exception e) {
            response = new LocalVideoSegmentResponse(403, e.getMessage());
        }



        return response;
    }

    private List<VideoSegment> getVideoSegments() throws Exception{
        logger.log("getting Video Segments");

        VideoSegmentDAO dao = new VideoSegmentDAO();

        return dao.getLocalVideoSegments();
    }
}
