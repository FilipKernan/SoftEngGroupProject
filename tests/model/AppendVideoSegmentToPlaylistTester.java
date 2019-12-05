package model;

import db.PlaylistDAO;
import db.PlaylistRelationDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class AppendVideoSegmentToPlaylistTester {
    private PlaylistRelationDAO plrDAO;

    public AppendVideoSegmentToPlaylistTester(){

    }

    @Before
    public void setupDB(){
        plrDAO = new PlaylistRelationDAO();
    }

    @Test
    public void quicklyTest(){
//        Playlist pl1 = new Playlist("What about the droid attack on the wookies?");
        Playlist pl1 = new Playlist("fe9029b1-754c-4872-b2fd-cacb316fdd6a", "test34");
        String vidID1 = "5251bbee-a9f7-4792-8833-b3335b984ac2";
        try{
            //Append video segment to playlist in DB
            System.out.println("Attempting to append video segment");
            plrDAO.appendVideoSegmentToPlaylist(pl1.getID(), vidID1);

            //TODO We can not test this until video segments can be added/deleted/retrieved from DB
            //TODO After this is done create video and pl in db, use their ids for the append statement above

            //Get all video segments associated with pl1
            System.out.println("Attempting to get all video segments with pl1 id");
            List<VideoSegment> vidSegs1 = plrDAO.getVidSegsInPlaylist(pl1.getID());

            //Print out all the vid segs
            System.out.println("\nAfter Append Segment");
            System.out.println("Print out all of the video segments");
            for (VideoSegment vid:vidSegs1) {
                System.out.println("Video Segment with ID " + vid.UUID);
            }

            //Try to delete the video segment associated with that playlist
            plrDAO.deleteVidSegInPlaylist(pl1.getID(),vidID1);

            //Get all video segments associated with pl1
            vidSegs1 = plrDAO.getVidSegsInPlaylist(pl1.getID());

            //Print out all the vid segs
            System.out.println("\n\nPrint out all of the video segments");
            System.out.println("After Delete");
            for (VideoSegment vid:vidSegs1) {
                System.out.println("Video Segment with ID " + vid.UUID);
            }

        } catch (Exception e){
            System.out.println("We got an exception, that's rather unfortunate.");
            System.out.println(e);
        }

    }
}