import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Converter {

    // FIX 1: Storage must be a field, not local, so it persists across calls.
    // FIX 3: Store short->long mapping to support redirection.
    private final Map<String, String> shortToLong = new HashMap<>();

    private static final String BASE_URL = "bit.ly/";
    private static final int SHORT_CODE_LENGTH = 7;

    public Response urlConverter(Request request) {
        // FIX 5: Input validation
        if (request == null || request.getRequestURL() == null || request.getRequestURL().isEmpty()) {
            throw new IllegalArgumentException("Request URL must not be null or empty");
        }

        String originalURL = request.getRequestURL();
        LocalDateTime originalTimestamp = request.getTimestamp();

        // FIX 1 & 2: Generate a short, unique code and check against the persistent map
        String shortCode;
        do {
            shortCode = randomGenerator();
        } while (shortToLong.containsKey(shortCode));

        shortToLong.put(shortCode, originalURL);

        Response response = new Response();
        response.setResponseURL(BASE_URL + shortCode);
        response.setExpiry(originalTimestamp.plusDays(45));
        return response;
    }

    // FIX 3: Redirection — given a short URL, return the original long URL
    public String redirect(String shortURL) {
        if (shortURL == null) return null;
        // Accept full URL like "bit.ly/abc1234" or just the code
        String code = shortURL.startsWith(BASE_URL) ? shortURL.substring(BASE_URL.length()) : shortURL;
        String longURL = shortToLong.get(code);
        if (longURL == null) {
            throw new IllegalArgumentException("Short URL not found: " + shortURL);
        }
        return longURL;
    }

    // FIX 2 & 4: Shorten the code to 7 chars; no unused url param
    private String randomGenerator() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, SHORT_CODE_LENGTH);
    }
}
