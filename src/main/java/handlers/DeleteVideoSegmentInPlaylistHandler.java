package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistRelationDAO;
import http.DeleteVideoSegmentInPlaylistRequest;
import http.DeleteVideoSegmentInPlaylistResponse;

public class DeleteVideoSegmentInPlaylistHandler implements RequestHandler<DeleteVideoSegmentInPlaylistRequest, DeleteVideoSegmentInPlaylistResponse> {

    public LambdaLogger logger;

    boolean appendVidSeg(String playlistID, String videoID) throws Exception {
        PlaylistRelationDAO dao = new PlaylistRelationDAO();

        return dao.deleteVidSegInPlaylist(playlistID, videoID);
    }

    @Override
    public DeleteVideoSegmentInPlaylistResponse handleRequest(DeleteVideoSegmentInPlaylistRequest input, Context context){
        DeleteVideoSegmentInPlaylistResponse response;
        String videoID = input.getVideoID();
        String playlistID = input.getPlaylistID();

        try{
            boolean success = appendVidSeg(playlistID, videoID);
            if(success){
                response = new DeleteVideoSegmentInPlaylistResponse(videoID, 200);
            } else {
                response = new DeleteVideoSegmentInPlaylistResponse(videoID, 409);
            }

        } catch (Exception e) {
            response = new DeleteVideoSegmentInPlaylistResponse(403, e.getMessage());
        }
        return response;
    }
}
