package router;

/*
* Phase 2:
*
* Designing the Core Router
* Let's dive into low-level design and Java coding.
* We will start by designing the Routing Engine.
* The fundamental job of the router is:
* Receive an incoming HTTP request (with a specific URI path and HTTP Method).
* Match it against a collection of configured rules.
* Identify the correct downstream service URL.
*
* Let's look at a conceptual configuration layout:
*
* /api/v1/users/  forwards to http://user-service:8081
*
* /api/v1/orders/ forwards to http://order-service:8082
*
* Your Task & Question:
*
* To make this highly performance-efficient and production-grade,
*  we need to design the data structures and classes.
* Sketch out the basic Java classes/interfaces needed to represent a Route and a RoutingEngine.
* What Java data structure would you use inside your RoutingEngine to store these routes so that when a request comes in,
* looking up the correct route is as fast and thread-safe as possible?
* Write out the interface or class skeleton code in Java.
*
* Don't worry about writing every single line of implementation yet—just show me the structural design and choice of data structures.
* */


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoutingEngine {
    // Hint: Routes change rarely (low writes) but are read millions of times (high reads).
    private final List<Route> routes = new CopyOnWriteArrayList<>();

    public void addRoute(Route route) {
        routes.add(route);
    }

    /**
     * Finds the matching target URI for an incoming request.
     * @return target service URI string, or null/empty if no route matches.
     */
    public String route(String incomingPath, String httpMethod) {

        for(Route route: routes) {
            if (route.matches(incomingPath, httpMethod)) {
                return route.getTargetUri();
            }
        }

        return null;
    }
}
