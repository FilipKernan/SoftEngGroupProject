package http;

import model.ThirdPartySite;

import java.util.ArrayList;
import java.util.List;

public class GetThirdPartySitesResponse {

    public final List<ThirdPartySite> list;
    public final int statusCode;
    public final String error;

    public GetThirdPartySitesResponse(List<ThirdPartySite> list, int code) {
        this.list = list;
        this.statusCode = code;
        this.error = "";
    }

    public GetThirdPartySitesResponse(int code, String errorMessage) {
        this.list = new ArrayList<>();
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (list == null) { return "EmptySites"; }
        return "AllSites(" + list.size() + ")";
    }

}
