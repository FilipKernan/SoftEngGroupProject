package db;

import handlers.AppendVideoSegmentToPlaylistHandler;
import handlers.CreatePlaylistHandler;
import handlers.DeletePlaylistHandler;
import handlers.DeleteVideoSegmentInPlaylistHandler;
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

        //append a video to the playlist created
        AppendVideoToPlaylistRequest append = new AppendVideoToPlaylistRequest(resp.playlist.getID(), "111111111111111111111111111111111111");
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
    }
}
