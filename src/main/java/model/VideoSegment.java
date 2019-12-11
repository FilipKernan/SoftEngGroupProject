package model;

public class VideoSegment {

    public final String url;
    public final String UUID;
    public final String transcript;
    public final String character;
    public final boolean isMarked;

    public VideoSegment(String url, String UUID, String transcript, String character) {
        this.url = url;
        this.UUID = UUID;
        this.transcript = transcript;
        this.character = character;
        this.isMarked = false;
    }

    public VideoSegment(String url, String UUID, String transcript, String character, boolean isMarked) {
        this.url = url;
        this.UUID = UUID;
        this.transcript = transcript;
        this.character = character;
        this.isMarked = isMarked;
    }
}
