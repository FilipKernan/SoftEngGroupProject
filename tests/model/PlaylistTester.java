package model;

import com.amazonaws.services.dynamodbv2.xspec.S;
import db.PlaylistDAO;
import org.junit.Test;

import java.util.List;

public class PlaylistTester {

    @Test
    public void createPlaylist(){
        Playlist pl = new Playlist("New Playlist");
    }

    @Test
    public void playlistDB(){
        PlaylistDAO plDAO = new PlaylistDAO();
        try{
            System.out.println("Test getting all of the playlists");
            List<Playlist> playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTest creating a new playlist and adding it to playlists");
            Playlist newPlaylist = new Playlist("Hello There");
            System.out.println("Newly Created Playlist: \n\tName:" + newPlaylist.getName() + "\n\tID: " + newPlaylist.getID());
            plDAO.createPlaylist(newPlaylist);
            playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

            System.out.println("\nTest deleting a playlist");
            plDAO.deletePlaylist(newPlaylist.getID());
            System.out.println("We will be deleting playlist with name Hello There and ID " + newPlaylist.getID());
            playlists = plDAO.getAllPlaylists();
            for (Playlist pl: playlists ) {
                System.out.println(pl.getName() + " " + pl.getID());
            }

        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
    }
}
