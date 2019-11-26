package http;

import model.VideoSegment;

import java.util.ArrayList;
import java.util.List;

public class LocalVideoSegmentResponse {

    public final List<VideoSegment> list;
    public final int statusCode;
    public final String error;

    public LocalVideoSegmentResponse( int statusCode, String error) {
        this.list = new ArrayList<>();
        this.statusCode = statusCode;
        this.error = error;
    }

    public LocalVideoSegmentResponse(List<VideoSegment> list, int statusCode) {
        this.list = list;
        this.statusCode = statusCode;
        this.error = "";
    }

    @Override
    public String toString() {
        if (list == null) {
            return "EmptyVideoSegments";
        }
        return "AllVideoSegments(" + list.size() + ")";
    }
}