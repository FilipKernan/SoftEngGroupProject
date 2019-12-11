package http;

public class CreateVideoSegmentLocalRequest {

    public String base64EncodedValue;
    public String character;
    public String transcript;


    public void setBase64Encodedvalue(String base64Encodedvalue) {
        this.base64EncodedValue = base64Encodedvalue;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getBase64Encodedvalue() {
        return base64EncodedValue;
    }

    public String getCharacter() {
        return character;
    }

    public String getTranscript() {
        return transcript;
    }

    public CreateVideoSegmentLocalRequest(String base64Encodedvalue, String id, String character, String transcript, String name) {
        this.base64EncodedValue = base64Encodedvalue;
        this.character = character;
        this.transcript = transcript;
    }

    public CreateVideoSegmentLocalRequest() {
    }


}
