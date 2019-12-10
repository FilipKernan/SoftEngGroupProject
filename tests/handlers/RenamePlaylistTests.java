package handlers;

import db.LambdaTest;
import http.GetAllPlaylistRequest;
import http.GetAllPlaylistsResponse;
import http.RenamePlaylistRequest;
import http.RenamePlaylistResponse;
import model.Playlist;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RenamePlaylistTests extends LambdaTest {

    @Test
    public void initialTesT(){
        GetAllPlaylistRequest cvs1 = new GetAllPlaylistRequest();
        GetAllPlaylistsResponse resp1 = new GetAllPlaylistsHandler().handleRequest(cvs1, createContext("GetPlaylists"));
        Assert.assertEquals(200, resp1.statusCode);
        List<Playlist> playlistList = resp1.list;

        String plID = playlistList.get(0).getID();
        String plOriginalName = playlistList.get(0).getName();
        RenamePlaylistRequest reqRN1 = new RenamePlaylistRequest(plID, "uniqueNewNamePLEASE");
        RenamePlaylistResponse respRN1 = new RenamePlaylistHandler().handleRequest(reqRN1, createContext("GetPlaylists"));
        System.out.println(respRN1.error);
        Assert.assertEquals(200, respRN1.statusCode);

        RenamePlaylistRequest reqRN2 = new RenamePlaylistRequest(plID, plOriginalName);
        RenamePlaylistResponse respRN2 = new RenamePlaylistHandler().handleRequest(reqRN2, createContext("GetPlaylists"));
        Assert.assertEquals(200, respRN2.statusCode);

    }
}
