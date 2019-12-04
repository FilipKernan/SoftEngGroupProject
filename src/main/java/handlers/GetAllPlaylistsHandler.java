package handlers;

import com.amazonaws.services.lambda.runtime.*;
import db.PlaylistDAO;
import http.GetAllPlaylistsResponse;
import model.Playlist;

import java.util.List;

public class GetAllPlaylistsHandler implements RequestHandler<Object, GetAllPlaylistsResponse> {

    public LambdaLogger logger;

    List<Playlist> getPlaylists() throws Exception {
        PlaylistDAO dao = new PlaylistDAO();

        return dao.getAllPlaylists();
    }

    @Override
    public GetAllPlaylistsResponse handleRequest(Object input, Context context){
       GetAllPlaylistsResponse response;
        try{
            List<Playlist> list = getPlaylists();
            response = new GetAllPlaylistsResponse(list, 200);

        } catch (Exception e) {
            response = new GetAllPlaylistsResponse(403, e.getMessage());
        }
        return response;
    }
}