package router;


import java.util.regex.Pattern;

public class Route {

    private final String id;
    private final String pathPattern; // e.g., "/api/v1/users/.*"
    private final String targetUri;   // e.g., "http://user-service:8081"
    private final String httpMethod;  // e.g., "GET", "POST"
    private final Pattern compiledPattern; // Pre-compiled for high-performance regex matching

    public Route(String id, String pathPattern, String targetUri, String httpMethod) {
        this.id = id;
        this.pathPattern = pathPattern;
        this.targetUri = targetUri;
        this.httpMethod = httpMethod;
        this.compiledPattern = Pattern.compile(pathPattern);
    }


    public boolean matches(String requestPath, String method) {
        return this.httpMethod.equalsIgnoreCase(method) &&
                compiledPattern.matcher(requestPath).matches();
    }

    public String getTargetUri() { return targetUri; }

}
