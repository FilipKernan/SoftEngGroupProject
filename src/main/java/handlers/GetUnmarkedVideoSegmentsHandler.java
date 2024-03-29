package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.VideoSegmentDAO;
import http.GetUnmarkedVideoSegmentsRequest;
import http.GetUnmarkedVideoSegmentsResponse;
import model.Segment;
import model.VideoSegment;

import java.util.List;

public class GetUnmarkedVideoSegmentsHandler implements RequestHandler<GetUnmarkedVideoSegmentsRequest, GetUnmarkedVideoSegmentsResponse> {
    public LambdaLogger logger;

    @Override
    public GetUnmarkedVideoSegmentsResponse handleRequest(GetUnmarkedVideoSegmentsRequest getLocalVideoSegmentsRequest, Context context) {

        logger = context.getLogger();

        logger.log("Handling GetUnmarkedVideoSegments lambda");

        GetUnmarkedVideoSegmentsResponse response;
        try {
            List<Segment> segments = getVideoSegments();
            response = new GetUnmarkedVideoSegmentsResponse(segments, 200);
        } catch (Exception e) {
            response = new GetUnmarkedVideoSegmentsResponse(403, e.getMessage());
        }



        return response;
    }

    private List<Segment> getVideoSegments() throws Exception{
        logger.log("getting Video Segments");

        VideoSegmentDAO dao = new VideoSegmentDAO();

        return dao.getUnmarkedVideoSegments();
    }
}
