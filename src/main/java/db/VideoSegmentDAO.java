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


    public VideoSegment generateVideoSegment(ResultSet resultSet) throws Exception {
        String UUID = resultSet.getString("videoID");
        String character = resultSet.getString("character");
        String videoUrl = resultSet.getString("videoUrl");
        String transcript = resultSet.getString("transcript");

        return new VideoSegment(videoUrl, UUID, transcript, character);
    }


}
