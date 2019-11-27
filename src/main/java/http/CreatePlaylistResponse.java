package http;

import model.Playlist;

public class CreatePlaylistResponse {

    public final Playlist playlist;
    public final int statusCode;
    public final String error;

    public CreatePlaylistResponse(Playlist playlist, int code) {
        this.playlist = playlist;
        this.statusCode = code;
        this.error = "";
    }

    public CreatePlaylistResponse(int code, String errorMessage) {
        this.playlist = null;
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (playlist == null) { return "No Playlist"; }
        return "Playlist:" + playlist.toString();
    }

}
