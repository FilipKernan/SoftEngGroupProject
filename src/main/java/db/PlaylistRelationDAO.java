package db;

import model.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRelationDAO {
    //data access object

    Connection conn;

    public PlaylistRelationDAO() {
        try  {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            conn = null;
        }
    }

    public boolean deletePlaylist(String id) throws Exception {
        try {
            PreparedStatement psName = conn.prepareStatement("DELETE FROM playlist WHERE playlistID = ?;");
            psName.setString(1, id);
            PreparedStatement psVideos = conn.prepareStatement("DELETE FROM playlistRelation WHERE playlistID = ?;");
            psVideos.setString(1, id);
            int numAffected = psName.executeUpdate();
            psName.close();
            psVideos.close();

            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public boolean createPlaylist(Playlist playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE playlistName = ?;");
            ps.setString(1, playlist.getName());
            ResultSet resultSet = ps.executeQuery();

            // already present?
            while (resultSet.next()) {
                Playlist c = generatePlaylist(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO playlist (playlistID,playlistName) values(?,?);");
            ps.setString(1,  playlist.getID());
            ps.setString(2,  playlist.getName());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public boolean appendVideoSegmentToPlaylist(String playlistID, String videoID) throws Exception {
        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlistRelation WHERE playlistID = ? AND videoID = ?;");
//            ps.setString(1, playlist.getID());
//            ps.setString(2, playlist.getID());
//            ResultSet resultSet = ps.executeQuery();
//
//            // already present?
//            while (resultSet.next()) {
//                Playlist c = generatePlaylist(resultSet);
//                resultSet.close();
//                return false;
//            }

            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlistRelation (playlistID,videoID) values(?,?);");
            ps.setString(1,  playlistID);
            ps.setString(2,  videoID);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<Playlist> getAllPlaylists() throws Exception {

        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM playlist";
            //TODO change the name of this later? We would have to change the name of the table but it should be plural
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Playlist c = generatePlaylist(resultSet);
                allPlaylists.add(c);
            }
            resultSet.close();
            statement.close();
            return allPlaylists;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }

    private boolean updatePlaylist(ResultSet resultSet, ArrayList<Playlist> allPlaylists) throws Exception {
        String id  = resultSet.getString("playlistID");
        String name  = resultSet.getString("playlistName");
        String videoID = resultSet.getString("videoID");
        for (Playlist pl: allPlaylists) {
            if(pl.getID().equals(id)){
                return pl.addVideoSegment(videoID);
            }
        }
        allPlaylists.add(new Playlist (id, videoID, name));
        return true;
    }

    //Create playlist with name and id from result set
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        String id  = resultSet.getString("playlistID");
        String name  = resultSet.getString("playlistName");
        return new Playlist(id, name);
    }

}
