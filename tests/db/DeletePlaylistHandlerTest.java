package db;

import handlers.CreatePlaylistHandler;
import handlers.DeletePlaylistHandler;
import http.CreatePlaylistRequest;
import http.CreatePlaylistResponse;
import http.DeletePlaylistRequest;
import http.DeletePlaylistResponse;
import org.junit.Assert;
import org.junit.Test;

public class DeletePlaylistHandlerTest extends LambdaTest {

    @Test
    public void testDeletePlaylist(){
        CreatePlaylistRequest cpr = new CreatePlaylistRequest("testName1");
        CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        //delete the playlist created
        DeletePlaylistRequest delete = new DeletePlaylistRequest(resp.playlist.getID());
        DeletePlaylistResponse deleteResp = new DeletePlaylistHandler().handleRequest(delete, createContext("detele"));
        Assert.assertEquals(200, deleteResp.statusCode);
    }
}
