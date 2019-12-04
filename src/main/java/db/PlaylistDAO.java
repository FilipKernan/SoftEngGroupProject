package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Playlist;

public class PlaylistDAO {
    //data access object

    java.sql.Connection conn;

    public PlaylistDAO() {
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
//            PreparedStatement psVideos = conn.prepareStatement("DELETE FROM playlistRelation WHERE playlistID = ?;");
//            psVideos.setString(1, id);
            int numAffected = psName.executeUpdate();
            psName.close();
//            psVideos.close();

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
            throw new Exception("Failed to insert playlist: " + e.getMessage());
        }
    }

    public List<Playlist> getAllPlaylists() throws Exception {

        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM playlist";
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

    //Create playlist with name and id from result set
    private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
        String id  = resultSet.getString("playlistID");
        String name  = resultSet.getString("playlistName");
        return new Playlist(id, name);
    }

}
