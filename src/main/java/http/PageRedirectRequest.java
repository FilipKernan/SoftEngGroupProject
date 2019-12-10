package http;

public class PageRedirectRequest {

    String page;


    public PageRedirectRequest(String page) {
        this.page = page;
    }


    public PageRedirectRequest() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
