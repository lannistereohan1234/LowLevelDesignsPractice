import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {
    private final ConcurrentHashMap<String, Integer> requestCounts = new ConcurrentHashMap<>();

    public void count(Request request) {
        if (request == null || request.getIdentity() == null || request.getIdentity().isBlank()) {
            return;
        }

        requestCounts.merge(request.getIdentity(), 1, Integer::sum);    //one liner for counting requests per id
    }



    public Map<String, Integer> snapshot() {
        return Map.copyOf(requestCounts);
    }

    // populate hashmap
    public void populate() {
        for (int i = 0; i < 50; i++) {
            count(new Request(UUID.randomUUID().toString(), LocalDateTime.now().plusNanos(i)));
        }
    }

}
