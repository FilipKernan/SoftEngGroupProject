package http;

import model.Playlist;

import java.util.ArrayList;
import java.util.List;

public class DeletePlaylistResponse {

    public final String id;
    public final int statusCode;
    public final String error;

    public DeletePlaylistResponse(String id, int code) {
        this.id = id;
        this.statusCode = code;
        this.error = "";
    }

    public DeletePlaylistResponse(int code, String errorMessage) {
        this.id = "-1";
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (id == null) { return "No ID"; }
        return "ID:" + id;
    }

}
