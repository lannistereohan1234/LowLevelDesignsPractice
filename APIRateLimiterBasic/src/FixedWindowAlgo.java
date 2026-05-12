import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowAlgo {
    private final int limit;
    private final long windowSizeInMillis;

    // Map the combined Key (ID + Window) directly to a counter
    private final ConcurrentHashMap<String, AtomicInteger> clientCounters = new ConcurrentHashMap<>();

    public FixedWindowAlgo(int limit, long windowSizeInMillis) {
        this.limit = limit;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public boolean isAllowed(String clientId) {
        // 1. Create a time-based bucket ID
        long windowId = System.currentTimeMillis() / windowSizeInMillis;

        // 2. Unique key per user per window (e.g., "user1:171552300")
        String key = clientId + ":" + windowId;

        // 3. Get existing counter or create a new one, then increment
        return clientCounters.computeIfAbsent(key, k -> new AtomicInteger(0))
                .incrementAndGet() <= limit;
    }
}
