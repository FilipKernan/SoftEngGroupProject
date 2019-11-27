package handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import db.PlaylistDAO;
import http.CreatePlaylistRequest;
import http.CreatePlaylistResponse;
import http.DeletePlaylistRequest;
import http.DeletePlaylistResponse;
import model.Playlist;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {

    public LambdaLogger logger;

    boolean createPlaylist(Playlist playlist) throws Exception {
        PlaylistDAO dao = new PlaylistDAO();

        return dao.createPlaylist(playlist);
    }

    @Override
    public CreatePlaylistResponse handleRequest(CreatePlaylistRequest input, Context context){
        CreatePlaylistResponse response;
        String name = input.getName();
        Playlist playlist = new Playlist(name);

        try{
            boolean success = createPlaylist(playlist);
            if(success){
                response = new CreatePlaylistResponse(playlist, 200);
            } else {
                response = new CreatePlaylistResponse(playlist, 409);
            }

        } catch (Exception e) {
            response = new CreatePlaylistResponse(403, e.getMessage());
        }
        return response;
    }
}