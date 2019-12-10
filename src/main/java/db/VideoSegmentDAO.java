package db;

import model.VideoSegment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VideoSegmentDAO {

    java.sql.Connection conn;

    public VideoSegmentDAO() {
        try {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            conn = null;
        }
    }

    public boolean deleteVideoSegment(String id) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM video WHERE videoID = ?;");
            ps.setString(1, id);
            int numAffected = ps.executeUpdate();
            ps.close();

            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public List<VideoSegment> getLocalVideoSegments() throws Exception{
        List<VideoSegment> localSegments = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM video WHERE ifLocal = 1";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                VideoSegment v = generateVideoSegment(resultSet);
                localSegments.add(v);
            }
            resultSet.close();
            statement.close();
            return localSegments;
        } catch (Exception e) {
            throw new Exception("Failed to get video segment:" + e.getMessage());
        }
    }

    public List<VideoSegment> getUnmarkedVideoSegments() throws Exception{
        List<VideoSegment> localSegments = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM video WHERE ifMarked = 0";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                VideoSegment v = generateVideoSegment(resultSet);
                localSegments.add(v);
            }
            resultSet.close();
            statement.close();
            return localSegments;
        } catch (Exception e) {
            throw new Exception("Failed to get video segment:" + e.getMessage());
        }
    }

    public boolean markVideoSegment(String id) throws Exception{
        try {
            PreparedStatement ps = conn.prepareStatement("Update video SET `ifMarked` = '1' WHERE videoID = ?;");
            ps.setString(1, id);
            int numAffected = ps.executeUpdate();
            ps.close();

            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public boolean unMarkVideoSegment(String id) throws Exception{
        try {
            PreparedStatement ps = conn.prepareStatement("Update video SET `ifMarked` = '0' WHERE videoID = ?;");
            ps.setString(1, id);
            int numAffected = ps.executeUpdate();
            ps.close();

            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }


    public VideoSegment generateVideoSegment(String character, String transcript, String id, String url) throws Exception {
        return new VideoSegment(url, id, transcript, character);

    }

    public VideoSegment generateVideoSegment(ResultSet resultSet) throws Exception {
        String UUID = resultSet.getString("videoID");
        String character = resultSet.getString("character");
        String videoUrl = resultSet.getString("videoUrl");
        String transcript = resultSet.getString("transcript");

        return new VideoSegment(videoUrl, UUID, transcript, character);
    }

    public boolean addVideoSegmentLocal(VideoSegment newVideoSegment) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM video WHERE videoID = ?;");
            ps.setString(1, newVideoSegment.UUID);
            ResultSet resultSet = ps.executeQuery();

            // already present?
            while (resultSet.next()) {
                VideoSegment c = generateVideoSegment(resultSet);
                resultSet.close();
                return false;
            }

//            ps =conn.prepareStatement("INSERT INTO video (videoID,character,transcript,videoURL,ifMarked,ifLocal) values(?,?,?,?,'0','1');");
//            ps =conn.prepareStatement("INSERT INTO video (videoID, character,transcript,videoURL,ifMarked,ifLocal) values(?,'character','transcript','url','0','1');");
            ps =conn.prepareStatement("INSERT INTO video (`videoID`,`character`,`transcript`,`videoURL`,`ifMarked`,`ifLocal`) values(?, ?, ?, ?, '0', '1');");
//            ps =conn.prepareStatement("INSERT INTO video WHERE videoID=? AND character=? AND transcript=? AND url=? AND ifMarker=? AND ifLocal=?;");
            ps.setString(1, newVideoSegment.UUID);
            ps.setString(2, newVideoSegment.character);
            ps.setString(3, newVideoSegment.transcript);
            ps.setString(4, newVideoSegment.url);
//            ps.setInt(5, 0);
//            ps.setInt(6, 0);

            ps.execute();
            ps.close();

            return true;


        } catch (Exception e){
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }

    public boolean addVideoSegment(VideoSegment newVideoSegment, int isLocal) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM video WHERE videoID = ?;");
            ps.setString(1, newVideoSegment.UUID);
            ResultSet resultSet = ps.executeQuery();

            // already present?
            while (resultSet.next()) {
                VideoSegment c = generateVideoSegment(resultSet);
                resultSet.close();
                return false;
            }

            ps =conn.prepareStatement("INSERT INTO video (`videoID`,`character`,`transcript`,`videoURL`,`ifMarked`,`ifLocal`) values(?, ?, ?, ?, '0', ?);");
            ps.setString(1, newVideoSegment.UUID);
            ps.setString(2, newVideoSegment.character);
            ps.setString(3, newVideoSegment.transcript);
            ps.setString(4, newVideoSegment.url);
            ps.setInt(5, isLocal);
            ps.execute();
            ps.close();

            return true;
        } catch (Exception e){
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }
}
