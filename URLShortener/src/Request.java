import java.time.LocalDateTime;

public class Request {
    private String requestURL;      // this will be the redirect url as well
    private LocalDateTime timestamp;

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Request(String requestURL, LocalDateTime timestamp){
        this.requestURL=requestURL;
        this.timestamp=timestamp;

    }





}
