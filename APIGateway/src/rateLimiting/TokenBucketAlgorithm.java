package rateLimiting;

import java.time.Instant;

public class TokenBucketAlgorithm {
    private final long capacity;
    private final double refillRatePerSecond;

    private double currentTokens;
    private Instant lastRefillTime;


    public TokenBucketAlgorithm(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.currentTokens = capacity;
        this.lastRefillTime = Instant.now();
    }


    public synchronized boolean allowRequest(){
        refill();

        if(currentTokens>=1.0){
            currentTokens-=1.0;
            return true;
        }

        return false;
    }

    public void refill(){
        Instant now = Instant.now();
        long elapsedMillis = java.time.Duration.between(lastRefillTime, now).toMillis();

        // Convert elapsed time to fractional tokens
        double tokensToAdd = (elapsedMillis / 1000.0) * refillRatePerSecond;

        // Caps the tokens to the max capacity of the bucket
        currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
        lastRefillTime = now;
    }
}
