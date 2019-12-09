package db;

import handlers.*;
import http.*;
import org.junit.Assert;
import org.junit.Test;


public class DeleteVideoSegmentInPlaylistHandlerTester extends LambdaTest{

    @Test
    public void testDeleteVideoInPlaylist(){
        //create a playlist
        CreatePlaylistRequest cpr = new CreatePlaylistRequest("testDeleteVSInPlaylist");
        CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        //upload video segment
        CreateVideoSegmentRequest cvs = new CreateVideoSegmentRequest("VGVzdERlbGV0ZQ==", "000000000000000000000000000000000001", "TestCharacter","TestName0", "TestTranscript");
        CreateVideoSegmentResponce uploadResp = new UploadVideoSegmentHandler().handleRequest(cvs, createContext("Upload"));
        Assert.assertEquals(200, uploadResp.statusCode);

        //append a video to the playlist created
        AppendVideoToPlaylistRequest append = new AppendVideoToPlaylistRequest(resp.playlist.getID(), uploadResp.videoID);
        AppendVideoToPlaylistResponse appendResp = new AppendVideoSegmentToPlaylistHandler().handleRequest(append, createContext("append"));
        Assert.assertEquals(200, appendResp.statusCode);

        //delete the video appended in the playlist
        DeleteVideoSegmentInPlaylistRequest deleteVSInPL = new DeleteVideoSegmentInPlaylistRequest(resp.playlist.getID(), appendResp.videoID);
        DeleteVideoSegmentInPlaylistResponse deleteVSInPLResp = new DeleteVideoSegmentInPlaylistHandler().handleRequest(deleteVSInPL, createContext("deleteVSInPL"));
        Assert.assertEquals(200, deleteVSInPLResp.statusCode);


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
