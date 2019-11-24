package http;

public class RegisterThirdPartyRequest {
    private boolean addTPS;
    private String url;

    public RegisterThirdPartyRequest(String url, boolean addTPS){
        this.url = url;
        this.addTPS = addTPS;
    }


    public boolean isAddTPS() {
        return addTPS;
    }

    public String getUrl() {
        return url;
    }
}
