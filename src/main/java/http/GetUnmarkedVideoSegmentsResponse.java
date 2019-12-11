package http;

import model.Segment;
import model.VideoSegment;

import java.util.ArrayList;
import java.util.List;

public class GetUnmarkedVideoSegmentsResponse {

    public final List<Segment> segments;
    public final int statusCode;
    public final String error;

    public GetUnmarkedVideoSegmentsResponse(int statusCode, String error) {
        this.segments = new ArrayList<>();
        this.statusCode = statusCode;
        this.error = error;
    }

    public GetUnmarkedVideoSegmentsResponse(List<Segment> list, int statusCode) {
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
