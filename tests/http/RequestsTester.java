package http;

import org.junit.Assert;
import org.junit.Test;

public class RequestsTester {

    @Test
    public void appendVideoToPlaylist(){
        AppendVideoToPlaylistRequest req = new AppendVideoToPlaylistRequest();
        AppendVideoToPlaylistRequest req2 = new AppendVideoToPlaylistRequest("playlistID", "videoID");

        req.setPlaylistID("playlistID");
        req.setVideoID("videoID");

        Assert.assertEquals(req.getPlaylistID(), req2.getPlaylistID());
        Assert.assertEquals(req.getVideoID(), req2.getVideoID());

        System.out.println(req.toString());
    }

    @Test
    public void createPlaylist(){
        CreatePlaylistRequest req = new CreatePlaylistRequest();
        CreatePlaylistRequest req2 = new CreatePlaylistRequest("name");

        req.setName("name");

        Assert.assertEquals(req.getName(), req2.getName());

        System.out.println(req.toString());
    }

    @Test
    public void deletePlaylist(){
        DeletePlaylistRequest req = new DeletePlaylistRequest();
        DeletePlaylistRequest req2 = new DeletePlaylistRequest("id");

        req.setId("id");

        Assert.assertEquals(req.getID(), req2.getID());

        System.out.println(req.toString());
    }

    @Test
    public void deleteVideoSegmentInPlaylist(){
        DeleteVideoSegmentInPlaylistRequest req = new DeleteVideoSegmentInPlaylistRequest();
        DeleteVideoSegmentInPlaylistRequest req2 = new DeleteVideoSegmentInPlaylistRequest("playlistID", "videoID");

        req.setPlaylistID("playlistID");
        req.setVideoID("videoID");

        Assert.assertEquals(req.getPlaylistID(), req2.getPlaylistID());
        Assert.assertEquals(req.getVideoID(), req2.getVideoID());

        System.out.println(req.toString());
    }

    @Test
    public void deleteVideoSegment(){
        DeleteVideoSegmentRequest req = new DeleteVideoSegmentRequest();
        DeleteVideoSegmentRequest req2 = new DeleteVideoSegmentRequest("id");

        req.setId("id");

        Assert.assertEquals(req.getID(), req2.getID());
        System.out.println(req.toString());

    }

    @Test
    public void createVideoSegment(){
        String base64EncodedValue = "value";
        String id = "id";
        String character = "character";
        String transcript = "transcript";
        String name = "name";



        CreateVideoSegmentLocalRequest req = new CreateVideoSegmentLocalRequest(base64EncodedValue, id, character, transcript, name);
        CreateVideoSegmentLocalRequest req2 = new CreateVideoSegmentLocalRequest();

        req2.setBase64Encodedvalue(base64EncodedValue);
        req2.setId(id);
        req2.setCharacter(character);
        req2.setTranscript(transcript);
        req2.setName(name);

        Assert.assertEquals(req.getBase64Encodedvalue(), req2.getBase64Encodedvalue());
        Assert.assertEquals(req.getId(), req2.getId());
        Assert.assertEquals(req.getCharacter(), req2.getCharacter());
        Assert.assertEquals(req.getTranscript(), req2.getTranscript());
        Assert.assertEquals(req.getName(), req2.getName());

        System.out.println(req.toString());

    }

    @Test
    public void getAllPlaylist(){
        GetAllPlaylistRequest req = new GetAllPlaylistRequest();
        System.out.println(req.toString());
    }

    @Test
    public void getAllVideoSegmentsInPlaylist(){
        GetAllVideoSegmentsInPlaylistRequest req = new GetAllVideoSegmentsInPlaylistRequest();
        GetAllVideoSegmentsInPlaylistRequest req2 = new GetAllVideoSegmentsInPlaylistRequest("id");

        req.setPlID("id");

        Assert.assertEquals(req.getPlID(), req2.getPlID());
        System.out.println(req.toString());

    }

    @Test
    public void getThirdPartySites(){
        GetThirdPartySitesRequest req = new GetThirdPartySitesRequest();
        System.out.println(req.toString());
    }

    @Test
    public void registerThirdPartyRequest(){
        RegisterThirdPartyRequest req = new RegisterThirdPartyRequest();
        RegisterThirdPartyRequest req2 = new RegisterThirdPartyRequest("url", false);
        RegisterThirdPartyRequest req3 = new RegisterThirdPartyRequest("url", true);

        req.setUrl("url");
        req.setAddTPS(false);

        Assert.assertEquals(req.getUrl(), req2.getUrl());
        Assert.assertEquals(req.isAddTPS(), req2.isAddTPS());
        Assert.assertTrue(req3.isAddTPS());
        System.out.println(req.toString());

    }

    @Test
    public void markSegmentRequest(){
        MarkSegmentRequest req = new MarkSegmentRequest();
        MarkSegmentRequest req2 = new MarkSegmentRequest("id", false);
        MarkSegmentRequest req3 = new MarkSegmentRequest("id", true);

        req.setId("id");
        req.setMakeMarked(false);

        Assert.assertEquals(req.getID(), req2.getID());
        Assert.assertEquals(req.isMakeMarked(), req2.isMakeMarked());
        Assert.assertTrue(req3.isMakeMarked());
        System.out.println(req.toString());

    }




}
