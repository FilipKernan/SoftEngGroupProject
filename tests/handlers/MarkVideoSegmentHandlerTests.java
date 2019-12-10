package handlers;

import db.LambdaTest;
import http.*;
import model.VideoSegment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MarkVideoSegmentHandlerTests extends LambdaTest {
    @Test
    public void initialTest(){
        GetLocalVideoSegmentsRequest cvs2 = new GetLocalVideoSegmentsRequest();
        GetLocalVideoSegmentsResponse resp2 = new GetLocalVideoSegmentsHandler().handleRequest(cvs2, createContext("Get VSs in PL"));
        Assert.assertEquals(200, resp2.statusCode);
        List<VideoSegment> videoList1 = resp2.list;

        String videoID = videoList1.get(0).UUID;
        MarkSegmentRequest reqMM1 = new MarkSegmentRequest(videoID, true);
        MarkSegmentResponse respMM1 = new MarkSegmentHandler().handleRequest(reqMM1, createContext("Mark Video Segment"));
        Assert.assertEquals(200, respMM1.statusCode);
        System.out.println(respMM1.toString());

        MarkSegmentRequest reqMM2 = new MarkSegmentRequest(videoID, false);
        MarkSegmentResponse respMM2 = new MarkSegmentHandler().handleRequest(reqMM2, createContext("Mark Video Segment"));
        Assert.assertEquals(200, respMM2.statusCode);
        System.out.println(respMM2.toString());

    }
}
