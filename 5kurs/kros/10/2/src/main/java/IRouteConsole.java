import java.util.List;


public interface IRouteConsole extends Console {

    void createRoutes(int routesCounts);

    List<Route> getByRouteNumber(Integer num);

    void printRoutes();

    void printRoutes(List<Route> routes);

    void clearRoutes();
}
