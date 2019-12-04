package http;

public class DeletePlaylistRequest {
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public DeletePlaylistRequest(String id){
        this.id = id;
    }

    public DeletePlaylistRequest(){

    }



    public String getID() {
        return id;
    }
}
