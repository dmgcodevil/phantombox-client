import java.text.MessageFormat;
import java.util.Set;

public class Menu {

    private JavaConsole javaConsole = new JavaConsole();

    private PriceHolder priceHolder = new PriceHolder();

    private static final String NO_PRICE_SCHEDULES = "no prices!";

    public void showMenu() {
        int item;
        do {
            printMenu();
            item = javaConsole.readInteger("Select menu item: ");
            switch (item) {
                case 1: {
                    addPrice();
                    break;
                }
                case 2: {
                    printPrices();
                    break;
                }
                case 3: {
                    getPricesByNumber();
                    break;
                }
                case 4: {
                    System.exit(0);
                }
                default: {
                    javaConsole.print("Wrong item!");
                }
            }

        } while (true);
    }


    private void printMenu() {
        javaConsole.print("1. Add price.");
        javaConsole.print("2. Print all prices.");
        javaConsole.print("3. Gets prices by store name.");
        javaConsole.print("4. Exit.");
    }

    private void addPrice() {
        String itemName = javaConsole.readLine("enter item name: ");
        String storeName = javaConsole.readLine("enter store name");
        Double price = javaConsole.readDouble("enter price");
        priceHolder.addPrice(new Price(itemName, storeName, price));
    }

    private void printPrices() {
        printPrices(priceHolder.getPrices());
    }

    private void printPrices(Set<Price> prices) {
        if (CollectionUtils.isNotEmpty(prices)) {
            for (Price price : prices) {
                javaConsole.print(MessageFormat.format("item name: {0}, store name: {1}, price: {2}",
                        price.getItemName(), price.getStoreName(), price.getPrice()));
            }
        } else {
            javaConsole.print(NO_PRICE_SCHEDULES);
        }

    }

    private void getPricesByNumber() {
        if (CollectionUtils.isNotEmpty(priceHolder.getPrices())) {
            String storeName = javaConsole.readLine("Enter store name: ");
            printPrices(priceHolder.filterPricesByStoreName(storeName));
        } else {
            javaConsole.print(NO_PRICE_SCHEDULES);
        }
    }


}
