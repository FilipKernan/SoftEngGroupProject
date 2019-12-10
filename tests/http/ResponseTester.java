package http;

import model.Playlist;
import model.ThirdPartySite;
import model.VideoSegment;
import org.junit.Test;

import java.net.URL;
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
    public void renamePlaylist(){
        RenamePlaylistResponse response = new RenamePlaylistResponse("playlistID", 200);
        RenamePlaylistResponse response2 = new RenamePlaylistResponse(403, "Bad things happened");

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
    public void createVideoSegmentLocal(){
        CreateVideoSegmentLocalResponse response = new CreateVideoSegmentLocalResponse("videoID", 200);
        CreateVideoSegmentLocalResponse response2 = new CreateVideoSegmentLocalResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void createVideoSegment(){
        CreateVideoSegmentResponse response = new CreateVideoSegmentResponse("videoID", 200);
        CreateVideoSegmentResponse response2 = new CreateVideoSegmentResponse(403, "Bad things happened");

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
        GetLocalVideoSegmentsResponse response = new GetLocalVideoSegmentsResponse(vids, 200);
        GetLocalVideoSegmentsResponse response2 = new GetLocalVideoSegmentsResponse(403, "Bad things happened");
        GetLocalVideoSegmentsResponse response3 = new GetLocalVideoSegmentsResponse(null, 200);

        System.out.println(response.toString());
        System.out.println(response2.toString());
        System.out.println(response3.toString());
    }

    @Test
    public void getUnmarkedVideoSegmentsRequest(){
        List<VideoSegment> vids = new ArrayList<>();
        GetUnmarkedVideoSegmentsResponse response = new GetUnmarkedVideoSegmentsResponse(vids, 200);
        GetUnmarkedVideoSegmentsResponse response2 = new GetUnmarkedVideoSegmentsResponse(403, "Bad things happened");
        GetUnmarkedVideoSegmentsResponse response3 = new GetUnmarkedVideoSegmentsResponse(null, 200);

        System.out.println(response.toString());
        System.out.println(response2.toString());
        System.out.println(response3.toString());
    }

    @Test
    public void registerThirdPartyResponse(){
        RegisterThirdPartyResponse response = new RegisterThirdPartyResponse("url", 200);
        RegisterThirdPartyResponse response2 = new RegisterThirdPartyResponse("url", 403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void pageRedirectResponse(){
        URL url = null;
        PageRedirectResponce response = new PageRedirectResponce(url, 200);
        PageRedirectResponce response2 = new PageRedirectResponce(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }

    @Test
    public void markSegmentResponse(){
        MarkSegmentResponse response = new MarkSegmentResponse("id", 200);
        MarkSegmentResponse response2 = new MarkSegmentResponse(403, "Bad things happened");

        System.out.println(response.toString());
        System.out.println(response2.toString());
    }




}
