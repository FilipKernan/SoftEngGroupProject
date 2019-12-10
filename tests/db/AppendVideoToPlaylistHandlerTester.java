package db;

import handlers.*;
import http.*;
import org.junit.Assert;
import org.junit.Test;

public class AppendVideoToPlaylistHandlerTester extends LambdaTest{
    @Test
    public void testAppendVideoToPlaylist(){
        //create a playlist
        CreatePlaylistRequest cpr = new CreatePlaylistRequest("testAppend");
        CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        CreateVideoSegmentLocalRequest cvs = new CreateVideoSegmentLocalRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName0", "TestTranscript");
        CreateVideoSegmentLocalResponse uploadResp = new CreateVideoSegmentLocalHandler().handleRequest(cvs, createContext("Upload"));
        Assert.assertEquals(200, uploadResp.statusCode);

        //append a video to the playlist created
        AppendVideoToPlaylistRequest append = new AppendVideoToPlaylistRequest(resp.playlist.getID(), uploadResp.videoID);
        AppendVideoToPlaylistResponse appendResp = new AppendVideoSegmentToPlaylistHandler().handleRequest(append, createContext("append"));
        Assert.assertEquals(200, appendResp.statusCode);

        //append a video that already existed in the playlist
        AppendVideoToPlaylistRequest append2 = new AppendVideoToPlaylistRequest(resp.playlist.getID(), "111111111111111111111111111111111111");
        AppendVideoToPlaylistResponse appendResp2 = new AppendVideoSegmentToPlaylistHandler().handleRequest(append2, createContext("append"));
        Assert.assertEquals(403, appendResp2.statusCode);

        //delete the playlist created
        DeletePlaylistRequest delete = new DeletePlaylistRequest(resp.playlist.getID());
        DeletePlaylistResponse deleteResp = new DeletePlaylistHandler().handleRequest(delete, createContext("detele"));
        Assert.assertEquals(200, deleteResp.statusCode);

        //delete the video segment upload
        DeleteVideoSegmentRequest dvs = new DeleteVideoSegmentRequest(uploadResp.videoID);
        DeleteVideoSegmentResponse dvsr = new DeleteVideoSegmentHandler().handleRequest(dvs, createContext("deleteVS"));
        Assert.assertEquals(200, dvsr.statusCode);
    }

}
