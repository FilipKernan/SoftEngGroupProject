package http;

public class CreateVideoSegmentRequest {



    private String base64EncodedValue;
    private String character;
    private String transcript;
    private boolean isLocal;
    public String tpsURL;


    public void setBase64Encodedvalue(String base64Encodedvalue) {
        this.base64EncodedValue = base64Encodedvalue;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public void setTpsURL(String tpsURL) {
        this.tpsURL = tpsURL;
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

    public String getTpsURL() {
        return tpsURL;
    }
    public boolean isLocal() {
        return isLocal;
    }


    public CreateVideoSegmentRequest(String base64Encodedvalue, String id, String character, String transcript, String name, boolean isLocal, String tpsURL) {
        this.base64EncodedValue = base64Encodedvalue;
        this.character = character;
        this.transcript = transcript;
        this.isLocal = isLocal;
        this.tpsURL = tpsURL;
    }

    public CreateVideoSegmentRequest() {
    }


}
