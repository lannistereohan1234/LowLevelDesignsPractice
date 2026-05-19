package filter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FilterChainProcessor {
    private final List<GatewayFilter> filters;

    public FilterChainProcessor(List<GatewayFilter> filters) {
        // Sort filters upfront by their order so they execute in the correct sequence
        this.filters = filters.stream()
                .sorted((f1, f2) -> Integer.compare(f1.getOrder(), f2.getOrder()))
                .collect(Collectors.toList());
    }

    public CompletableFuture<Void> executeChain(RequestContext context) {
        CompletableFuture<Void> chainFuture = CompletableFuture.completedFuture(null);

        for (GatewayFilter filter : filters) {
            // sequential composition: chain each filter's future execution to the previous one
            chainFuture = chainFuture.thenCompose(v -> filter.filter(context));
        }

        return chainFuture;
    }
}
