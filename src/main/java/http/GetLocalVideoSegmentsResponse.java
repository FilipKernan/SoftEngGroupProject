package http;

import model.VideoSegment;

import java.util.ArrayList;
import java.util.List;

public class GetLocalVideoSegmentsResponse {

    public final List<VideoSegment> segments;
    public final int statusCode;
    public final String error;

    public GetLocalVideoSegmentsResponse(int statusCode, String error) {
        this.segments = new ArrayList<>();
        this.statusCode = statusCode;
        this.error = error;
    }

    public GetLocalVideoSegmentsResponse(List<VideoSegment> list, int statusCode) {
        this.segments = list;
        this.statusCode = statusCode;
        this.error = "";
    }

    @Override
    public String toString() {
        if (segments == null) {
            return "EmptyVideoSegments";
        }
        return "AllVideoSegments(" + segments.size() + ")";
    }
}
