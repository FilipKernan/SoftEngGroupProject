package http;

import model.VideoSegment;

import java.util.ArrayList;
import java.util.List;

public class GetAllVideoSegmentsInPlaylistResponse {

    public final List<VideoSegment> list;
    public final int statusCode;
    public final String error;

    public GetAllVideoSegmentsInPlaylistResponse(List<VideoSegment> list, int code) {
        this.list = list;
        this.statusCode = code;
        this.error = "";
    }

    public GetAllVideoSegmentsInPlaylistResponse(int code, String errorMessage) {
        this.list = new ArrayList<>();
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (list == null) { return "NoVidSegs"; }
        return "AllVidSegs(" + list.size() + ")";
    }

}
