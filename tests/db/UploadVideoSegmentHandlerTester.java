package db;

import handlers.DeleteVideoSegmentHandler;
import handlers.UploadVideoSegmentHandler;
import http.CreateVideoSegmentRequest;
import http.CreateVideoSegmentResponce;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;
import org.junit.Assert;
import org.junit.Test;

public class UploadVideoSegmentHandlerTester extends LambdaTest{

    @Test
    public void testUpload() {
        CreateVideoSegmentRequest cvs = new CreateVideoSegmentRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName", "TestTranscript");
        CreateVideoSegmentResponce resp = new UploadVideoSegmentHandler().handleRequest(cvs, createContext("Upload"));
        Assert.assertEquals(200, resp.statusCode);

        //test repetitive input of character and transcript
        CreateVideoSegmentRequest cvsR = new CreateVideoSegmentRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter", "TestName", "TestTranscript");
        CreateVideoSegmentResponce respR = new UploadVideoSegmentHandler().handleRequest(cvsR, createContext("Upload"));
        Assert.assertEquals(422, respR.statusCode);

        DeleteVideoSegmentRequest dvs = new DeleteVideoSegmentRequest(resp.videoID);
        DeleteVideoSegmentResponse dvsr = new DeleteVideoSegmentHandler().handleRequest(dvs, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr.statusCode);
    }


}
