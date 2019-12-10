package http;

public class RenamePlaylistRequest {
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    private String newName;

    public RenamePlaylistRequest(String id, String newName){
        this.id = id;
        this.newName = newName;
    }

    public RenamePlaylistRequest(){

    }



    public String getID() {
        return id;
    }
}
