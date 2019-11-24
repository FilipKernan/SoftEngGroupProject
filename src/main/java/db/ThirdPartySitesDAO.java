package db;

import model.Playlist;
import model.ThirdPartySite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThirdPartySitesDAO {
    //data access object

    Connection conn;

    public ThirdPartySitesDAO() {
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
    public boolean deleteThirdPartySite(ThirdPartySite tps) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM thirdPartyLibrary WHERE SiteID = ?;");
            ps.setString(1, tps.getID());
            int numAffected = ps.executeUpdate();
            ps.close();

            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public boolean addThirdPartySite(ThirdPartySite tps) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM thirdPartyLibrary WHERE siteID = ?;");
            ps.setString(1, tps.getID());
            ResultSet resultSet = ps.executeQuery();

            // already present?
            while (resultSet.next()) {
//                Playlist c = generatePlaylist(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Constants (siteID,url,videoID) values(?,?,?);");
            ps.setString(1,  tps.getID());
            ps.setString(2,  tps.getUrl());
            ps.setString(3,  null);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<ThirdPartySite> getAllThirdPartySites() throws Exception {

        ArrayList<ThirdPartySite> allTPS = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM thirdPartyLibrary";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                try{
                    updateThirdPartySite(resultSet, allTPS);
                } catch (Exception e) {
                    throw new Exception("Failed in adding playlists: " + e.getMessage());
                }
            }
            resultSet.close();
            statement.close();
            return allTPS;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }

    private boolean updateThirdPartySite(ResultSet resultSet, ArrayList<ThirdPartySite> allTPS) throws Exception {
        String id  = resultSet.getString("siteID");
        String url  = resultSet.getString("url");
        String videoID = resultSet.getString("videoID");
        for (ThirdPartySite tps: allTPS) {
            if(tps.getID().equals(id)){
                return tps.addVideoSegment(videoID);
            }
        }
        allTPS.add(new ThirdPartySite(url, id, videoID));
        return true;
    }

}
