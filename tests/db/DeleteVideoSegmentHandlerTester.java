package db;

import handlers.CreateVideoSegmentHandler;
import handlers.DeleteVideoSegmentHandler;
import handlers.CreateVideoSegmentLocalHandler;
import http.*;
import org.junit.Assert;
import org.junit.Test;

public class DeleteVideoSegmentHandlerTester extends LambdaTest{

    @Test
    public void testDeleteVS(){

        CreateVideoSegmentLocalRequest cvs1 = new CreateVideoSegmentLocalRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName0", "TestTranscript");
        CreateVideoSegmentLocalResponse resp1 = new CreateVideoSegmentLocalHandler().handleRequest(cvs1, createContext("Upload"));
        Assert.assertEquals(200, resp1.statusCode);

        DeleteVideoSegmentRequest dvs1 = new DeleteVideoSegmentRequest(resp1.videoID);
        DeleteVideoSegmentResponse dvsr1 = new DeleteVideoSegmentHandler().handleRequest(dvs1, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr1.statusCode);

        CreateVideoSegmentRequest cvs2 = new CreateVideoSegmentRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName0", "TestTranscript", true, null);
        CreateVideoSegmentResponse resp2 = new CreateVideoSegmentHandler().handleRequest(cvs2, createContext("Upload"));
        Assert.assertEquals(200, resp2.statusCode);

        DeleteVideoSegmentRequest dvs2 = new DeleteVideoSegmentRequest(resp2.videoID);
        DeleteVideoSegmentResponse dvsr2 = new DeleteVideoSegmentHandler().handleRequest(dvs2, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr2.statusCode);

    }
}
