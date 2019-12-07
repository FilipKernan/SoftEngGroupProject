package db;

import handlers.DeleteVideoSegmentHandler;
import handlers.UploadVideoSegmentHandler;
import http.CreateVideoSegmentRequest;
import http.CreateVideoSegmentResponce;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;
import org.junit.Assert;
import org.junit.Test;

public class DeleteVideoSegmentHandlerTester extends LambdaTest{

    @Test
    public void testDeleteVS(){

        CreateVideoSegmentRequest cvs = new CreateVideoSegmentRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName0", "TestTranscript");
        CreateVideoSegmentResponce resp = new UploadVideoSegmentHandler().handleRequest(cvs, createContext("Upload"));
        Assert.assertEquals(200, resp.statusCode);

        DeleteVideoSegmentRequest dvs = new DeleteVideoSegmentRequest(resp.videoID);
        DeleteVideoSegmentResponse dvsr = new DeleteVideoSegmentHandler().handleRequest(dvs, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr.statusCode);

    }
}
