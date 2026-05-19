package filter;

import java.util.concurrent.CompletableFuture;

public class AuthFilter implements GatewayFilter {

    @Override
    public CompletableFuture<Void> filter(RequestContext context) {
        if (context.getHeader("X-Auth-Token") != null) {
            // Success path: return an already completed future
            return CompletableFuture.completedFuture(null);
        }

        // Failure path: return an already failed future containing our exception
        return CompletableFuture.failedFuture(new SecurityException("Missing X-Auth-Token header"));
    }

    @Override
    public int getOrder() {
        return 1; // We want Authentication to run first in the pipeline
    }
}
