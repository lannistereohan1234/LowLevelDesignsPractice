package filter;

import java.util.concurrent.CompletableFuture;

public interface GatewayFilter {
    /**
     * Processes the request.
     * @param context Object containing request/response data and sharing state between filters.
     * @return A CompletableFuture that completes successfully if the chain should continue,
     *         or completes exceptionally/fails if the request is blocked (e.g., Auth failed).
     */
    CompletableFuture<Void> filter(RequestContext context);

    /**
     * Determines the execution order. Lower values run first.
     */
    int getOrder();
}
