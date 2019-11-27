package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistDAO;
import db.VideoSegmentDAO;
import http.DeletePlaylistRequest;
import http.DeletePlaylistResponse;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;

public class DeleteVideoSegmentHandler implements RequestHandler<DeleteVideoSegmentRequest, DeleteVideoSegmentResponse> {

    public LambdaLogger logger;

    boolean deleteVideoSegment(String id) throws Exception {
        VideoSegmentDAO dao = new VideoSegmentDAO();

        return dao.deleteVideoSegment(id);
    }

    @Override
    public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest input, Context context){
        DeleteVideoSegmentResponse response;
        String id = input.getID();
        try{
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