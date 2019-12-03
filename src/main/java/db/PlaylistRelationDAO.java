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

    public boolean deleteVidSegInPlaylist(String videoID, String playlistID) throws Exception {
        try {
            PreparedStatement psName = conn.prepareStatement("DELETE FROM playlistRelation WHERE playlistID=? AND videoID=?;");
            psName.setString(1, videoID);
            int numAffected = psName.executeUpdate();
            psName.close();

            return (numAffected >= 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public boolean appendVideoSegmentToPlaylist(String playlistID, String videoID) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlistRelation (playlistID,videoID) values(?,?);");
            ps.setString(1,  playlistID);
            ps.setString(2,  videoID);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<String> getVidSegsInPlaylist(String plID) throws Exception{
        try {
            List<String> vidSegs = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlistRelation WHERE playlistID=?;");
            ps.setString(1,  plID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                vidSegs.add(resultSet.getString("videoID"));
            }
            resultSet.close();
            ps.close();

            return vidSegs;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }

}
