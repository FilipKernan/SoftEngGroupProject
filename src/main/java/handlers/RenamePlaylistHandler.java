package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistDAO;
import http.RenamePlaylistRequest;
import http.RenamePlaylistResponse;

public class RenamePlaylistHandler implements RequestHandler<RenamePlaylistRequest, RenamePlaylistResponse> {

    public LambdaLogger logger;

    boolean renamePlaylist(String id, String newName) throws Exception {
        PlaylistDAO dao = new PlaylistDAO();

        return dao.renamePlaylist(id, newName);
    }

    @Override
    public RenamePlaylistResponse handleRequest(RenamePlaylistRequest input, Context context){
        RenamePlaylistResponse response;
        String id = input.getID();
        String newName = input.getNewName();
        try{
            boolean success = renamePlaylist(id, newName);
            if(success){
                response = new RenamePlaylistResponse(id, 200);
            } else {
                response = new RenamePlaylistResponse(409, "No Playlist with this ID exists");
            }

        } catch (Exception e) {
            response = new RenamePlaylistResponse(403, e.getMessage());
        }
        return response;
    }
}