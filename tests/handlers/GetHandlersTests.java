package handlers;

import db.LambdaTest;
import handlers.CreateVideoSegmentLocalHandler;
import http.*;
import model.Playlist;
import model.VideoSegment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GetHandlersTests extends LambdaTest {

    @Test
    public void initialTest(){
        GetAllPlaylistRequest cvs1 = new GetAllPlaylistRequest();
        GetAllPlaylistsResponse resp1 = new GetAllPlaylistsHandler().handleRequest(cvs1, createContext("GetPlaylists"));
        Assert.assertEquals(200, resp1.statusCode);
        List<Playlist> playlistList = resp1.list;

        for (Playlist p: playlistList) {
            System.out.println("Playlist ID: " + p.toString());
            GetAllVideoSegmentsInPlaylistRequest cvs2 = new GetAllVideoSegmentsInPlaylistRequest(p.getID());
            GetAllVideoSegmentsInPlaylistResponse resp2 = new GetAllVideoSegmentInPlaylist().handleRequest(cvs2, createContext("Get VSs in PL"));
            Assert.assertEquals(200, resp2.statusCode);
            List<VideoSegment> videoList1 = resp2.list;

            System.out.println("Videos in pl:");
            for (VideoSegment v: videoList1) {
                System.out.println(v.UUID);
            }
            System.out.println("\n");
        }

        GetUnmarkedVideoSegmentsRequest reqUM1 = new GetUnmarkedVideoSegmentsRequest();
        GetUnmarkedVideoSegmentsResponse respUM1 = new GetUnmarkedVideoSegmentsHandler().handleRequest(reqUM1, createContext("GetPlaylists"));
        Assert.assertEquals(200, resp1.statusCode);

        for (VideoSegment v: respUM1.list) {
            System.out.println(v.UUID);
        }

    }
}
