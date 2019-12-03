package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Library {
    private ArrayList<Playlist> playlists = null;
    private ArrayList<VideoSegment> videoSegments = null;

    public Library(){
        playlists  = new ArrayList<Playlist>();
        videoSegments = new ArrayList<VideoSegment>();
    }

    public boolean tryAddingPlaylist(String plID, String vidSegID){
        for (Playlist pl: playlists) {
            if(plID.equals(pl.getID())){
                return pl.addVideoSegment(vidSegID);
            }
        }

        playlists.add(new Playlist(plID, vidSegID));
        return true;
    }

    public boolean addPlaylist(Playlist pl){
        playlists.add(pl);
        return true;
    }

    public  boolean removePlaylist(String plID){
        for(int i = 0; i < playlists.size(); i++){
            if(playlists.get(i).getID() == plID){
                playlists.remove(i);
                return true;
            }
        }return false;
    }

    public ArrayList<Playlist> getPlaylistIDs(){
        return playlists;
    }

    public boolean importVideoSegment(VideoSegment vs){
        videoSegments.add(vs);
        return true;
    }

    public boolean deleteVideoSegment(String vsID){
        for(int i = 0; i < videoSegments.size(); i++){
            if(videoSegments.get(i).UUID == vsID){
                videoSegments.remove(i);
                return true;
            }
        }return false;
    }

    public ArrayList<VideoSegment> getVideoSegments(){
        return videoSegments;
    }
}
