package http;

public class CreatePlaylistRequest {
    private String name;

    public CreatePlaylistRequest(String name){
        this.name = name;
    }



    public String getName() {
        return name;
    }
}
