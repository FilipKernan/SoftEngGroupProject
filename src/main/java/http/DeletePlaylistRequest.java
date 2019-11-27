package http;

public class DeletePlaylistRequest {
    private String id;

    public DeletePlaylistRequest(String id){
        this.id = id;
    }



    public String getID() {
        return id;
    }
}
