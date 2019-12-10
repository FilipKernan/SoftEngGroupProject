package http;

import model.VideoSegment;

import java.util.ArrayList;
import java.util.List;

public class GetLocalVideoSegmentsResponse {

    public final List<VideoSegment> list;
    public final int statusCode;
    public final String error;

    public GetLocalVideoSegmentsResponse(int statusCode, String error) {
        this.list = new ArrayList<>();
        this.statusCode = statusCode;
        this.error = error;
    }

    public GetLocalVideoSegmentsResponse(List<VideoSegment> list, int statusCode) {
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
