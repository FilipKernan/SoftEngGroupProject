package db;

import handlers.CreatePlaylistHandler;
import handlers.DeletePlaylistHandler;
import http.CreatePlaylistRequest;
import http.CreatePlaylistResponse;
import http.DeletePlaylistRequest;
import http.DeletePlaylistResponse;
import org.junit.Assert;
import org.junit.Test;
import com.google.gson.Gson;

import java.io.IOException;

public class CreatePlaylistHandlerTest extends LambdaTest{
    void testSuccessInput(String incoming) throws IOException {
        CreatePlaylistHandler handler = new CreatePlaylistHandler();
        CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);

        CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }

    void testFailInput(String incoming, int failureCode) throws IOException {
        CreatePlaylistHandler handler = new CreatePlaylistHandler();
        CreatePlaylistRequest req = new Gson().fromJson(incoming, CreatePlaylistRequest.class);

        CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

    @Test
    public void testRepetitiveInput() {
        String var = "test";
        CreatePlaylistRequest ccr = new CreatePlaylistRequest(var);
        String SAMPLE_INPUT_STRING =  new Gson().toJson(ccr);

        try {
            testFailInput(SAMPLE_INPUT_STRING, 409);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }
    }

    @Test
    public void testLongInput() {
        String var = "loooooooooooooooooooooooooooooooooooooooooooooooongName";
        CreatePlaylistRequest ccr = new CreatePlaylistRequest(var);
        String SAMPLE_INPUT_STRING =  new Gson().toJson(ccr);

        try {
            testFailInput(SAMPLE_INPUT_STRING, 403);
        } catch (IOException ioe) {
            Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    @Test
    public void testCreatePlaylist(){
        CreatePlaylistRequest cpr = new CreatePlaylistRequest("testName1");
        CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        //delete the playlist created
        DeletePlaylistRequest delete = new DeletePlaylistRequest(resp.playlist.getID());
        DeletePlaylistResponse deleteResp = new DeletePlaylistHandler().handleRequest(delete, createContext("detele"));
        Assert.assertEquals(200, deleteResp.statusCode);
    }
}
