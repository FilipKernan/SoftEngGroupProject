package db;

import handlers.DeleteVideoSegmentHandler;
import handlers.CreateVideoSegmentLocalHandler;
import http.CreateVideoSegmentLocalRequest;
import http.CreateVideoSegmentLocalResponse;
import http.DeleteVideoSegmentRequest;
import http.DeleteVideoSegmentResponse;
import org.junit.Assert;
import org.junit.Test;

public class DeleteVideoSegmentHandlerTester extends LambdaTest{

    @Test
    public void testDeleteVS(){

        CreateVideoSegmentLocalRequest cvs = new CreateVideoSegmentLocalRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName0", "TestTranscript");
        CreateVideoSegmentLocalResponse resp = new CreateVideoSegmentLocalHandler().handleRequest(cvs, createContext("Upload"));
        Assert.assertEquals(200, resp.statusCode);

        DeleteVideoSegmentRequest dvs = new DeleteVideoSegmentRequest(resp.videoID);
        DeleteVideoSegmentResponse dvsr = new DeleteVideoSegmentHandler().handleRequest(dvs, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr.statusCode);

    }
}
