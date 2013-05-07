import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RouteConsole extends JavaConsole implements IRouteConsole {

    private List<Route> routes = Collections.emptyList();

    private RouteConsole() {
        super();
    }

    private static volatile RouteConsole instance;

    public static RouteConsole getInstance() {
        RouteConsole localInstance = instance;
        if (localInstance == null) {
            synchronized (RouteConsole.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RouteConsole();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void createRoutes(int routesCounts) {
        routes = holdRoute(routesCounts);
    }

    @Override
    public List<Route> getByRouteNumber(Integer num) {
        if (num == null) {
            throw new IllegalArgumentException("route cannot be null");
        }
        List<Route> foundRoutes = Collections.emptyList();
        if (routes != null && routes.size() > 0) {
            foundRoutes = new ArrayList(); // not lazy. better init if at least one exist
            for (Route route : routes) {
                if (num.equals(route.getNumber())) {
                    foundRoutes.add(route);
                }
            }
        } else {
            print("No routes exist.. Please add one...");
        }
        return foundRoutes;
    }

    @Override
    public void printRoutes() {
        printRoutes(routes);
    }

    @Override
    public void printRoutes(List<Route> pRoutes) {
        if (pRoutes != null && pRoutes.size() > 0) {
            Collections.sort(pRoutes);
            for (Route route : pRoutes) {
                print(MessageFormat.format("num: {0}, start: {1}, end: {2}",
                        route.getNumber(), route.getStart(), route.getEnd()));
            }
        } else {
            // nice catch.
            print("No routes found. To create a new route please select necessary menu item.");
        }
    }

    @Override
    public void clearRoutes() {
        routes = Collections.emptyList();
    }

    private Route holdRoute() {
        String start = readLineAndValidate("enter route start point name: ");
        String end = readLineAndValidate("enter route end point name: ");
        Integer number = readIntAndValidate("enter route number: ");
        return new Route(start, end, number);
    }

    private List<Route> holdRoute(int num) {
        if (num <= 0) {
            return Collections.emptyList();
        }
        List<Route> routes = new ArrayList();
        int current = 0;
        print("Create " + num + " routes... ");
        do {
            print("Create new route: " + (current + 1));
            routes.add(holdRoute());
            current++;
        } while (num != current);
        return routes;
    }

// this is the end...
}
