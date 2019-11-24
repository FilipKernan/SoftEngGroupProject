package model;

import java.util.List;

public class ThirdPartySite {

    private String url;
    private String ID;
    public List<String> videoSegmentIDs;

    public ThirdPartySite(String url, String ID, String videoSegmentID){
        this.url = url;
        this.ID = ID;
        videoSegmentIDs.add(videoSegmentID);
    }

    public String getUrl() {
        return url;
    }

    public String getID() {
        return ID;
    }

    public boolean addVideoSegment(String vidSegID){
        videoSegmentIDs.add(vidSegID);
        return true;
    }

}
