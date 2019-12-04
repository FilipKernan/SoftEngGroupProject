package http;

public class DeleteVideoSegmentRequest {
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public DeleteVideoSegmentRequest(String id){
        this.id = id;
    }

    public DeleteVideoSegmentRequest(){

    }


    public String getID() {
        return id;
    }
}
