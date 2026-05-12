import java.time.LocalDateTime;

public class Response {

    private String responseURL;
    private LocalDateTime expiry;

    public Response(String responseURL, LocalDateTime expiry) {
        this.responseURL = responseURL;
        this.expiry = expiry;
    }


    public Response() {

    }

    public String getResponseURL() {
        return responseURL;
    }

    public void setResponseURL(String responseURL) {
        this.responseURL = responseURL;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
}
