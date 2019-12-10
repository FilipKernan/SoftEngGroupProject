package http;

public class PageRedirectResponse {

    String url;
    int statusCode;
    String error;


    public PageRedirectResponse(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public PageRedirectResponse(String url, int statusCode) {
        this.url = url;
        this.statusCode = statusCode;
    }


    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setError(String error) {
        this.error = error;
    }
}
