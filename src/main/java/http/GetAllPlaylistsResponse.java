package http;

import model.Playlist;

import java.util.ArrayList;
import java.util.List;

public class GetAllPlaylistsResponse {

    public final List<Playlist> list;
    public final int statusCode;
    public final String error;

    public GetAllPlaylistsResponse(List<Playlist> list, int code) {
        this.list = list;
        this.statusCode = code;
        this.error = "";
    }

    public GetAllPlaylistsResponse(int code, String errorMessage) {
        this.list = new ArrayList<>();
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (list == null) { return "NoPlaylists"; }
        return "AllPlaylists(" + list.size() + ")";
    }

}
