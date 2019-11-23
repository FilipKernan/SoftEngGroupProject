package model;

import java.util.ArrayList;

public class Playlist {

    private String id;
    public ArrayList<String> videoSegmentIDs;

    public Playlist(String id, String vidSegID){
        this.id = id;
        videoSegmentIDs.add(vidSegID);
    }

    public String getID(){
        return id;
    }

    public boolean addVideoSegment(String vidSegID){
        videoSegmentIDs.add(vidSegID);
        return true;
    }


}
