import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class RouteMenu implements IMenu {

    private IRouteConsoleHolder consoleHolder = new RouteConsoleHolder();

    private static final int CREATE_ROUTES_MENU_ITEM = 1;
    private static final int PRINT_ROUTES_MENU_ITEM = 2;
    private static final int GET_ROUTE_MENU_ITEM = 3;
    private static final int CLEAR_ROUTES_MENU_ITEM = 4;
    private static final int EXIT_MENU_ITEM = 5;

    private final Map<Integer, Command> commands;

    private static final Set<String> MENU_ITEMS = new LinkedHashSet<String>();

    public RouteMenu() {
        commands = new HashMap<Integer, Command>();
        commands.put(CREATE_ROUTES_MENU_ITEM, createRoutesCommand);
        commands.put(PRINT_ROUTES_MENU_ITEM, printRoutesCommand);
        commands.put(GET_ROUTE_MENU_ITEM, getRouteCommand);
        commands.put(CLEAR_ROUTES_MENU_ITEM, clearRoutesCommand);
        commands.put(EXIT_MENU_ITEM, exitCommand);

        // create menu items.. look as st ... unit with command init ?
        addMenuItem("==============[ MENU ]=============");
        addMenuItem(CREATE_ROUTES_MENU_ITEM, "Create routes");
        addMenuItem(PRINT_ROUTES_MENU_ITEM, "Print routes");
        addMenuItem(GET_ROUTE_MENU_ITEM, "Get route by number");
        addMenuItem(CLEAR_ROUTES_MENU_ITEM, "Clear routes");
        addMenuItem(EXIT_MENU_ITEM, "Exit");
        addMenuItem("===================================");
    }

    @Override
    public void showMenu() {
        int item;
        do {
            printMenu();
            item = consoleHolder.readIntAndValidate("Select menu item: ");
            Command command = commands.get(item);
            if (command != null) {
                command.execute();
            } else {
                consoleHolder.print("Wrong command!");
            }
        } while (true);
    }

    private static void addMenuItem(String caption) {
        MENU_ITEMS.add(caption);
    }

    private static void addMenuItem(int num, String caption) {
        MENU_ITEMS.add(num + ". " + caption);
    }

    private void printMenu() {
        for (String item : MENU_ITEMS) {
            consoleHolder.print(item);
        }
    }

    private Command createRoutesCommand = new Command() {
        @Override
        public void execute() {
            Integer count = consoleHolder.readIntAndValidate("Enter count of routes: ");
            consoleHolder.createRoutes(count);
        }
    };

    private Command printRoutesCommand = new Command() {
        @Override
        public void execute() {
            consoleHolder.printRoutes();
        }
    };

    private Command getRouteCommand = new Command() {
        @Override
        public void execute() {
            Integer num = consoleHolder.readIntAndValidate("Enter number of route: ");
            consoleHolder.printRoutes(consoleHolder.getByRouteNumber(num));
        }
    };

    private Command clearRoutesCommand = new Command() {
        @Override
        public void execute() {
            consoleHolder.clearRoutes();
        }
    };

    private Command exitCommand = new Command() {
        @Override
        public void execute() {
            System.exit(0);
        }
    };

}
