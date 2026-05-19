package filter;

import java.util.Map;

public class RequestContext {
    private final Map<String, String> headers;

    public RequestContext(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }
}
