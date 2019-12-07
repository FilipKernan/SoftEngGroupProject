package http;

public class CreateVideoSegmentRequest {



    public String base64EncodedValue;
    public String id;
    public String character;
    public String transcript;
    public String name;


    public void setBase64Encodedvalue(String base64Encodedvalue) {
        this.base64EncodedValue = base64Encodedvalue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getBase64Encodedvalue() {
        return base64EncodedValue;
    }

    public String getId() {
        return id;
    }

    public String getCharacter() {
        return character;
    }

    public String getTranscript() {
        return transcript;
    }

    public CreateVideoSegmentRequest(String base64Encodedvalue, String id, String character, String transcript, String name) {
        this.base64EncodedValue = base64Encodedvalue;
        this.id = id;
        this.character = character;
        this.transcript = transcript;
        this.name = name;
    }

    public CreateVideoSegmentRequest() {
    }


}
