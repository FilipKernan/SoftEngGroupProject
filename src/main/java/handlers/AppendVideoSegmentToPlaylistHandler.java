package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistRelationDAO;
import http.AppendVideoToPlaylistRequest;
import http.AppendVideoToPlaylistResponse;

public class AppendVideoSegmentToPlaylistHandler implements RequestHandler<AppendVideoToPlaylistRequest, AppendVideoToPlaylistResponse> {

    public LambdaLogger logger;

    boolean appendVidSeg(String playlistID, String videoID) throws Exception {
        PlaylistRelationDAO dao = new PlaylistRelationDAO();

        return dao.appendVideoSegmentToPlaylist(playlistID, videoID);
    }

    @Override
    public AppendVideoToPlaylistResponse handleRequest(AppendVideoToPlaylistRequest input, Context context){
        AppendVideoToPlaylistResponse response;
        String videoID = input.getVideoID();
        String playlistID = input.getPlaylistID();

        try{
            boolean success = appendVidSeg(playlistID, videoID);
            if(success){
                response = new AppendVideoToPlaylistResponse(videoID, 200);
            } else {
                response = new AppendVideoToPlaylistResponse(videoID, 409);
            }

        } catch (Exception e) {
            response = new AppendVideoToPlaylistResponse(403, e.getMessage());
        }
        return response;
    }
}