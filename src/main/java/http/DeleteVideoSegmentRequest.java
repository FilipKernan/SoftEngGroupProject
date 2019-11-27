package http;

public class DeleteVideoSegmentRequest {
    private String id;

    public DeleteVideoSegmentRequest(String id){
        this.id = id;
    }



    public String getID() {
        return id;
    }
}
