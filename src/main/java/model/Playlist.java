package model;

import java.util.ArrayList;

public class Playlist {

    private String id;
    private String name;
    public ArrayList<String> videoSegmentIDs;

    public Playlist(String id, String vidSegID){
        this.id = id;
        videoSegmentIDs.add(vidSegID);
    }

    public Playlist(String name){
        this.name = name;
    }

    public Playlist(String id, String vidSegID, String name){
        this.id = id;
        videoSegmentIDs.add(vidSegID);
        this.name = name;
    }

    public boolean renamePlaylist(String name){
        this.name = name;
        return true;
    }

    public String getID(){
        return id;
    }

    public boolean addVideoSegment(String vidSegID){
        videoSegmentIDs.add(vidSegID);
        return true;
    }


    public String getName() {
        return name;
    }
}
