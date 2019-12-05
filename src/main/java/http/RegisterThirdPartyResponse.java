package http;

public class RegisterThirdPartyResponse {

    public final String url;
    public final int statusCode;
    public final String error;

    public RegisterThirdPartyResponse (String url, int statusCode) {
        this.url = url;
        this.statusCode = statusCode;
        this.error = "";
    }

    // 200 means success
    public RegisterThirdPartyResponse (String name, int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.error = errorMessage;
        this.url = name;
    }

    public String toString() {
        if (statusCode / 100 == 2) {  // too cute?
            return "DeleteResponse(" + url + ")";
        } else {
            return "ErrorResult(" + url + ", statusCode=" + statusCode + ", err=" + error + ")";
        }
    }
}
