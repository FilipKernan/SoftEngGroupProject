package db;

import handlers.DeleteVideoSegmentHandler;
import handlers.CreateVideoSegmentLocalHandler;
import http.CreateVideoSegmentLocalRequest;
import http.CreateVideoSegmentLocalResponse;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;
import org.junit.Assert;
import org.junit.Test;

public class UploadVideoSegmentHandlerTester extends LambdaTest{

    @Test
    public void testUpload() {
        CreateVideoSegmentLocalRequest cvs = new CreateVideoSegmentLocalRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName", "TestTranscript");
        CreateVideoSegmentLocalResponse resp = new CreateVideoSegmentLocalHandler().handleRequest(cvs, createContext("Upload"));
        Assert.assertEquals(200, resp.statusCode);

        //test repetitive input of character and transcript
        CreateVideoSegmentLocalRequest cvsR = new CreateVideoSegmentLocalRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter", "TestName", "TestTranscript");
        CreateVideoSegmentLocalResponse respR = new CreateVideoSegmentLocalHandler().handleRequest(cvsR, createContext("Upload"));
        Assert.assertEquals(422, respR.statusCode);

        DeleteVideoSegmentRequest dvs = new DeleteVideoSegmentRequest(resp.videoID);
        DeleteVideoSegmentResponse dvsr = new DeleteVideoSegmentHandler().handleRequest(dvs, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr.statusCode);
    }


}
