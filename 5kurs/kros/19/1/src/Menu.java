import java.text.MessageFormat;
import java.util.Set;

public class Menu {

    private JavaConsole javaConsole = new JavaConsole();

    private TrainHolder trainHolder = new TrainHolder();

    private static final String NO_TRAIN_SCHEDULES = "no train schedules!";

    public void showMenu() {
        int item;
        do {
            printMenu();
            item = javaConsole.readInteger("Select menu item: ");
            switch (item) {
                case 1: {
                    addTrain();
                    break;
                }
                case 2: {
                    printTrains();
                    break;
                }
                case 3: {
                    getTrainsByNumber();
                    break;
                }
                case 4: {
                    getTrainsByDestination();
                    break;
                }
                case 5: {
                    System.exit(0);
                }
                default: {
                    javaConsole.print("Wrong item!");
                }
            }

        } while (true);
    }


    private void printMenu() {
        javaConsole.print("1. Add train schedule.");
        javaConsole.print("2. Print schedule.");
        javaConsole.print("3. Gets trains by number.");
        javaConsole.print("4. Gets trains by destination.");
        javaConsole.print("5. Exit.");
    }

    private void addTrain() {
        String start = javaConsole.readLine("enter train start name: ");
        String destination = javaConsole.readLine("enter train destination name: ");
        Integer number = javaConsole.readInteger("enter train number: ");
        trainHolder.addTrain(new Train(start, destination, number));
    }

    private void printTrains() {
        printTrains(trainHolder.getTrains());
    }

    private void printTrains(Set<Train> trains) {
        if (CollectionUtils.isNotEmpty(trains)) {
            for (Train train : trains) {
                javaConsole.print(MessageFormat.format("num: {0}, start: {1}, destination: {2}",
                        train.getNumber(), train.getStart(), train.getDestination()));
            }
        } else {
            javaConsole.print(NO_TRAIN_SCHEDULES);
        }

    }

    private void getTrainsByNumber() {
        if (CollectionUtils.isNotEmpty(trainHolder.getTrains())) {
            Integer num = javaConsole.readInteger("Enter number of train: ");
            printTrains(trainHolder.filterTrainsByNumber(num));
        } else {
            javaConsole.print(NO_TRAIN_SCHEDULES);
        }
    }

    private void getTrainsByDestination() {
        if (CollectionUtils.isNotEmpty(trainHolder.getTrains())) {
            String destination = javaConsole.readLine("Enter train destination: ");
            printTrains(trainHolder.filterTrainsByDestination(destination));
        } else {
            javaConsole.print(NO_TRAIN_SCHEDULES);
        }
    }

}
