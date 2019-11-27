package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistDAO;
import http.DeletePlaylistRequest;
import http.DeletePlaylistResponse;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse> {

    public LambdaLogger logger;

    boolean deletePlaylist(String id) throws Exception {
        PlaylistDAO dao = new PlaylistDAO();

        return dao.deletePlaylist(id);
    }

    @Override
    public DeletePlaylistResponse handleRequest(DeletePlaylistRequest input, Context context){
        DeletePlaylistResponse response;
        String id = input.getID();
        try{
            boolean success = deletePlaylist(id);
            if(success){
                response = new DeletePlaylistResponse(id, 200);
            } else {
                response = new DeletePlaylistResponse(id, 409);
            }

        } catch (Exception e) {
            response = new DeletePlaylistResponse(403, e.getMessage());
        }
        return response;
    }
}