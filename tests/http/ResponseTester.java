package http;

import model.Playlist;
import model.ThirdPartySite;
import model.VideoSegment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ResponseTester {

    @Test
    public void appendVideoToPlaylist(){
        AppendVideoToPlaylistResponse response = new AppendVideoToPlaylistResponse("videoID", 200);
        AppendVideoToPlaylistResponse response2 = new AppendVideoToPlaylistResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void createPlaylist(){
        CreatePlaylistResponse response = new CreatePlaylistResponse(new Playlist("Hello There"), 200);
        CreatePlaylistResponse response2 = new CreatePlaylistResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void deletePlaylist(){
        DeletePlaylistResponse response = new DeletePlaylistResponse("playlistID", 200);
        DeletePlaylistResponse response2 = new DeletePlaylistResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void deleteVideoSegmentInPlaylist(){
        DeleteVideoSegmentInPlaylistResponse response = new DeleteVideoSegmentInPlaylistResponse("videoID", 200);
        DeleteVideoSegmentInPlaylistResponse response2 = new DeleteVideoSegmentInPlaylistResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void deleteVideoSegment(){
        DeleteVideoSegmentResponse response = new DeleteVideoSegmentResponse("videoID", 200);
        DeleteVideoSegmentResponse response2 = new DeleteVideoSegmentResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void getAllPlaylists(){
        List<Playlist> playlists = new ArrayList<>();
        GetAllPlaylistsResponse response = new GetAllPlaylistsResponse(playlists, 200);
        GetAllPlaylistsResponse response2 = new GetAllPlaylistsResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void getAllVideoSegmentsInPlaylist(){
        List<VideoSegment> vids = new ArrayList<>();
        GetAllVideoSegmentsInPlaylistResponse response = new GetAllVideoSegmentsInPlaylistResponse(vids, 200);
        GetAllVideoSegmentsInPlaylistResponse response2 = new GetAllVideoSegmentsInPlaylistResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void getAllThirdPartySites(){
        List<ThirdPartySite> tps = new ArrayList<>();
        GetThirdPartySitesResponse response = new GetThirdPartySitesResponse(tps, 200);
        GetThirdPartySitesResponse response2 = new GetThirdPartySitesResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void getLocalVideoSegments(){
        List<VideoSegment> vids = new ArrayList<>();
        LocalVideoSegmentsResponse response = new LocalVideoSegmentsResponse(vids, 200);
        LocalVideoSegmentsResponse response2 = new LocalVideoSegmentsResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void registerThirdPartyResponse(){
        RegisterThirdPartyResponse response = new RegisterThirdPartyResponse("playlistID", 200);
        RegisterThirdPartyResponse response2 = new RegisterThirdPartyResponse("url", 403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }




}
