import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.populate();

        int limitPerWindow = 5;
        long windowSizeInMillis = 60_000;

        FixedWindowAlgo rateLimiter = new FixedWindowAlgo(limitPerWindow, windowSizeInMillis);

        int totalAllowed = 0;
        int totalBlocked = 0;

        Map<String, Integer> requestData = new HashMap<>();         // sad path + happy path map
        requestData.put("userA", 8); // limit is 5 => 3 should be blocked
        requestData.put("userB", 2); // all allowed

//        Map<String, Integer> requestData = repository.snapshot();     // Happy path map
        for (Map.Entry<String, Integer> entry : requestData.entrySet()) {
            String identity = entry.getKey();
            int requestCount = entry.getValue();

            int allowedForIdentity = 0;
            int blockedForIdentity = 0;

            for (int i = 0; i < requestCount; i++) {
                if (rateLimiter.isAllowed(identity)) {
                    allowedForIdentity++;
                    totalAllowed++;
                } else {
                    blockedForIdentity++;
                    totalBlocked++;
                }
            }

            System.out.printf("identity=%s, total=%d, allowed=%d, blocked=%d%n",
                    identity, requestCount, allowedForIdentity, blockedForIdentity);
        }

        System.out.printf("%nAggregate -> allowed=%d, blocked=%d%n", totalAllowed, totalBlocked);
    }
}
