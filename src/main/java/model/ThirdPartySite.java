package model;

import java.util.List;

public class ThirdPartySite {

    private String url;
    private String ID;

    public ThirdPartySite(String url, String ID){
        this.url = url;
        this.ID = ID;
    }

    public String getUrl() {
        return url;
    }

    public String getID() {
        return ID;
    }

}
