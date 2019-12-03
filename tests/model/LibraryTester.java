package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class LibraryTester {

    @Test
    public void PlaylistTester(){
        //add playlist
        Library l1 = new Library();
        Playlist pl1 = new Playlist("name1");
        Assert.assertNull(l1.getPlaylistIDs());
        l1.addPlaylist(pl1);
        Assert.assertEquals(1, l1.getPlaylistIDs().size());
        Assert.assertEquals(pl1, l1.getPlaylistIDs().get(0));

        //remove playlist
        l1.removePlaylist(pl1.getID());
        Assert.assertNull(l1.getPlaylistIDs());
    }

    @Test
    public void videoSegmentTester(){
        Library l2 = new Library();
        String url1 = "URl1";
        String vID = UUID.randomUUID().toString();
        String transcript1 = "Transcript1";
        String character1 = "Character1";
        VideoSegment v1 = new VideoSegment(url1, vID, transcript1, character1);

        //import video segment
        Assert.assertNull(l2.getVideoSegments());
        l2.importVideoSegment(v1);
        Assert.assertEquals(1, l2.getVideoSegments().size());
        Assert.assertEquals(v1, l2.getVideoSegments().get(0));

        //delete video segment
        l2.deleteVideoSegment(v1.UUID);
        Assert.assertNull(l2.getVideoSegments());


    }


}
