package http;

import java.net.URL;

public class PageRedirectResponce {

    URL url;
    int statusCode;
    String error;


    public PageRedirectResponce(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public PageRedirectResponce(URL url, int statusCode) {
        this.url = url;
        this.statusCode = statusCode;
    }
}
