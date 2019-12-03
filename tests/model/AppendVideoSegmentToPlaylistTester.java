package model;

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
        Playlist pl1 = new Playlist("What about the droid attack on the wookies?");
        String vidID1 = UUID.randomUUID().toString();
        try{
//            //Append video segment to playlist in DB
//            System.out.println("Attempting to append video segment");
//            plrDAO.appendVideoSegmentToPlaylist(pl1.getID(), vidID1);

            //TODO We can not test this until video segments can be added/deleted/retrieved from DB
            //TODO After this is done create video and pl in db, use their ids for the append statement above

            //Get all video segments associated with pl1
            System.out.println("Attempting to get all video segments with pl1 id");
            List<String> vidSegs1 = plrDAO.getVidSegsInPlaylist(pl1.getID());

            //Print out all the vid segs
            System.out.println("Print out all of the video segments");
            for (String vid:vidSegs1) {
                System.out.println("Video Segment with ID " + vid);
            }

//            //Try to delete the video segment associated with that playlist
//            plrDAO.deleteVidSegInPlaylist(pl1.getID(),vidID1);
//
//            //Get all video segments associated with pl1
//            vidSegs1 = plrDAO.getVidSegsInPlaylist(pl1.getID());
//
//            //Print out all the vid segs
//            for (String vid:vidSegs1) {
//                System.out.println("Video Segment with ID " + vid);
//            }
//
        } catch (Exception e){
            System.out.println("We got an exception, that's rather unfortunate.");
            System.out.println(e);
        }

    }
}