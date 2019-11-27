package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistDAO;
import http.AppendVideoToPlaylistRequest;
import http.AppendVideoToPlaylistResponse;
import http.CreatePlaylistRequest;
import http.CreatePlaylistResponse;
import model.Playlist;

public class AppendVideoSegmentToPlaylistHandler implements RequestHandler<AppendVideoToPlaylistRequest, AppendVideoToPlaylistResponse> {

    public LambdaLogger logger;

    boolean createPlaylist(String playlistID, String videoID) throws Exception {
        PlaylistDAO dao = new PlaylistDAO();

        return dao.appendVideoSegmentToPlaylist(playlistID, videoID);
    }

    @Override
    public AppendVideoToPlaylistResponse handleRequest(AppendVideoToPlaylistRequest input, Context context){
        AppendVideoToPlaylistResponse response;
        String videoID = input.getVideoID();
        String playlistID = input.getVideoID();

        try{
            boolean success = createPlaylist(playlistID, videoID);
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