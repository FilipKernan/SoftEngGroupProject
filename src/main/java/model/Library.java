package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Library {
    public ArrayList<Playlist> playlists = null;
    public ArrayList<VideoSegment> videoSegments = null;

    public Library(){

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

}
