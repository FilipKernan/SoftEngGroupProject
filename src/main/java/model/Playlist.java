package model;

import java.util.ArrayList;

public class Playlist {

    private String id;
    public ArrayList<String> videoSegmentURLs;

    public Playlist(String id, String videoSegmentURL){
        this.id = id;
        videoSegmentURLs.add(videoSegmentURL);
    }

    public String getID(){
        return id;
    }

    public boolean addVideoSegment(String videoSegURL){
        videoSegmentURLs.add(videoSegURL);
        return true;
    }


}
