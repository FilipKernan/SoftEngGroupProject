package db;

import handlers.AppendVideoSegmentToPlaylistHandler;
import handlers.CreatePlaylistHandler;
import handlers.DeletePlaylistHandler;
import http.*;
import org.junit.Assert;
import org.junit.Test;

public class AppendVideoToPlaylistHandlerTester extends LambdaTest{
    @Test
    public void testDeletePlaylist(){
        //create a playlist
        CreatePlaylistRequest cpr = new CreatePlaylistRequest("testName");
        CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        //append a video to the playlist created
        AppendVideoToPlaylistRequest append = new AppendVideoToPlaylistRequest(resp.playlist.getID(), "111111111111111111111111111111111111");
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
    }

}
