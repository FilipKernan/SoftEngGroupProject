package model;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {

    private String id;
    private String name;
    private ArrayList<String> videoSegmentIDs;

//    public Playlist(String id, String vidSegID){
//        this.id = id;
//        videoSegmentIDs.add(vidSegID);
//    }

    public Playlist(String id, String name){
        videoSegmentIDs = new ArrayList<>();
        this.id = id;
        this.name = name;
    }

    public Playlist(String name){
        videoSegmentIDs = new ArrayList<>();
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public Playlist(String id, String vidSegID, String name){
        videoSegmentIDs = new ArrayList<>();
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


    public boolean removeVideoSegment(String vidSegID){
        for(int i = 0; i < videoSegmentIDs.size(); i++){
            if(videoSegmentIDs.get(i) == vidSegID){
                videoSegmentIDs.remove(i);
                return true;
            }
        }return false;
    }
    public String getName() {
        return name;
    }

    public String toString(){
        return id;
    }

    public ArrayList<String> getVideoSegmentIDs() {
        return videoSegmentIDs;
    }

}
