import java.time.LocalDateTime;

public class Request {
    private String identity;        // this can be IP address or userid
    private LocalDateTime timestamp;

    public Request(){}

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Request(String identity, LocalDateTime timestamp) {
        this.identity = identity;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
