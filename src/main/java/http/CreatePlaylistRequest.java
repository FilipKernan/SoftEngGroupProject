package http;

public class CreatePlaylistRequest {
    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public CreatePlaylistRequest(String name){
        this.name = name;
    }

    public CreatePlaylistRequest(){

    }


    public String getName() {
        return name;
    }
}
