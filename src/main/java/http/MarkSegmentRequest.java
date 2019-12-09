package http;

public class MarkSegmentRequest {
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public boolean isMakeMarked() {
        return makeMarked;
    }

    public void setMakeMarked(boolean makeMarked) {
        this.makeMarked = makeMarked;
    }

    private boolean makeMarked;

    public MarkSegmentRequest(String id, boolean makeMarked){
        this.id = id;
        this.makeMarked = makeMarked;
    }

    public MarkSegmentRequest(){

    }

    public String getID() {
        return id;
    }
}
