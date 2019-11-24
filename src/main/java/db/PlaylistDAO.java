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

//    public Playlist getConstant(String name) throws Exception {
//
//        try {
//            Playlist playlist = null;
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Constants WHERE name=?;");
//            ps.setString(1,  name);
//            ResultSet resultSet = ps.executeQuery();
//
//            while (resultSet.next()) {
//                playlist = generatePlaylist(resultSet);
//            }
//            resultSet.close();
//            ps.close();
//
//            return playlist;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception("Failed in getting constant: " + e.getMessage());
//        }
//    }

//    public boolean updateConstant(Playlist constant) throws Exception {
//        try {
//            String query = "UPDATE Constants SET value=? WHERE name=?;";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setDouble(1, constant.value);
//            ps.setString(2, constant.name);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//
//            return (numAffected == 1);
//        } catch (Exception e) {
//            throw new Exception("Failed to update report: " + e.getMessage());
//        }
//    }
//
//    public boolean deleteConstant(Playlist constant) throws Exception {
//        try {
//            PreparedStatement ps = conn.prepareStatement("DELETE FROM Constants WHERE name = ?;");
//            ps.setString(1, constant.name);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//
//            return (numAffected == 1);
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert constant: " + e.getMessage());
//        }
//    }
//
//
//    public boolean addConstant(Playlist constant) throws Exception {
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Constants WHERE name = ?;");
//            ps.setString(1, constant.name);
//            ResultSet resultSet = ps.executeQuery();
//
//            // already present?
//            while (resultSet.next()) {
//                Playlist c = generatePlaylist(resultSet);
//                resultSet.close();
//                return false;
//            }
//
//            ps = conn.prepareStatement("INSERT INTO Constants (name,value) values(?,?);");
//            ps.setString(1,  constant.name);
//            ps.setDouble(2,  constant.value);
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert constant: " + e.getMessage());
//        }
//    }

    public List<Playlist> getAllPlaylists() throws Exception {

        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM playlist";
            //TODO change the name of this later? We would have to change the name of the table but it should be plural
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                try{
                    updatePlaylist(resultSet, allPlaylists);
                } catch (Exception e) {
                    throw new Exception("Failed in adding playlists: " + e.getMessage());
                }
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
        String videoID = resultSet.getString("videoID");
        for (Playlist pl: allPlaylists) {
            if(pl.getID().equals(id)){
                return pl.addVideoSegment(videoID);
            }
        }
        allPlaylists.add(new Playlist (id, videoID));
        return true;
    }

}
