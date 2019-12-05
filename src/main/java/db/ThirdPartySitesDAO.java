package db;

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

    public boolean deleteThirdPartySite(ThirdPartySite tps) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM thirdPartyLibrary WHERE siteID = ?;");
            ps.setString(1, tps.getID());
            int numAffected = ps.executeUpdate();
            ps.close();

            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete thirdPartySite: " + e.getMessage());
        }
    }


    public boolean addThirdPartySite(ThirdPartySite tps) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM thirdPartyLibrary WHERE siteID = ?;");
            ps.setString(1, tps.getID());
            ResultSet resultSet = ps.executeQuery();

            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO thirdPartyLibrary (siteID,url) values(?,?,?);");
            ps.setString(1,  tps.getID());
            ps.setString(2,  tps.getUrl());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert into thirdPartyLibrary: " + e.getMessage());
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
                    createThirdPartySite(resultSet, allTPS);
                } catch (Exception e) {
                    throw new Exception("Failed in adding third party sites: " + e.getMessage());
                }
            }
            resultSet.close();
            statement.close();
            return allTPS;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }

    private boolean createThirdPartySite(ResultSet resultSet, ArrayList<ThirdPartySite> allTPS) throws Exception {
        String id  = resultSet.getString("siteID");
        String url  = resultSet.getString("url");
        allTPS.add(new ThirdPartySite(url, id));
        return true;
    }

}
