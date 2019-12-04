package http;

public class RegisterThirdPartyRequest {
    public void setAddTPS(boolean addTPS) {
        this.addTPS = addTPS;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private boolean addTPS;
    private String url;

    public RegisterThirdPartyRequest(String url, boolean addTPS){
        this.url = url;
        this.addTPS = addTPS;
    }

    public RegisterThirdPartyRequest(){

    }


    public boolean isAddTPS() {
        return addTPS;
    }

    public String getUrl() {
        return url;
    }
}
