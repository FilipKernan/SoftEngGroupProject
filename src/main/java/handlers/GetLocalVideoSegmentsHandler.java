package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.VideoSegmentDAO;
import http.GetLocalVideoSegmentsResponse;
import http.GetLocalVideoSegmentsRequest;
import model.VideoSegment;

import java.util.List;

public class GetLocalVideoSegmentsHandler implements RequestHandler<GetLocalVideoSegmentsRequest, GetLocalVideoSegmentsResponse> {
    public LambdaLogger logger;

    @Override
    public GetLocalVideoSegmentsResponse handleRequest(GetLocalVideoSegmentsRequest getLocalVideoSegmentsRequest, Context context) {

        logger = context.getLogger();

        logger.log("Handling localVideoSegment lambda");

        GetLocalVideoSegmentsResponse response;
        try {
            List<VideoSegment> list = getVideoSegments();
            response = new GetLocalVideoSegmentsResponse(list, 200);
        } catch (Exception e) {
            response = new GetLocalVideoSegmentsResponse(403, e.getMessage());
        }



        return response;
    }

    private List<VideoSegment> getVideoSegments() throws Exception{
        logger.log("getting Video Segments");

        VideoSegmentDAO dao = new VideoSegmentDAO();

        return dao.getLocalVideoSegments();
    }
}
