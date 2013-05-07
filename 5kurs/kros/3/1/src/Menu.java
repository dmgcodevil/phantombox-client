import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class Menu {

    private JavaConsole javaConsole = new JavaConsole();

    private FileManager fileManager = new FileManager();

    private static final String NO_FILES = "no files!";

    public void showMenu() {
        int item;
        do {
            printMenu();
            item = javaConsole.readInteger("Select menu item: ");
            switch (item) {
                case 1: {
                   addFile();
                    break;
                }
                case 2: {
                    printFiles();
                    break;
                }
                case 3: {
                    getFileByName();
                    break;
                }
                case 4: {
                   getWithMaxCalls();
                    break;
                }
                case 5: {
                    removeFilesBefore();
                    break;
                }
                case 6: {
                    System.exit(0);
                }

                default: {
                    javaConsole.print("Wrong item!");
                }
            }

        } while (true);
    }


    private void printMenu() {
        javaConsole.print("1. Add file.");
        javaConsole.print("2. Print all files.");
        javaConsole.print("3. Get file by name.");
        javaConsole.print("4. Get file with more calls.");
        javaConsole.print("5. Remove files which was created before date.");
        javaConsole.print("6. Exit.");
    }

    private void addFile() {
        String fileName = javaConsole.readLine("enter file name: ");
        fileManager.addFile(new File(fileName));
    }


    private void printFiles() {
        if (fileManager.isFilesNotEmpty()) {
            for (File file : fileManager.getFiles()) {
                printFile(file);
            }
        } else {
            javaConsole.print(NO_FILES);
        }

    }

    private void printFile(File file) {
        javaConsole.print(MessageFormat.format("file name: {0}, created: {1}, number of calls: {2}",
                file.getName(), file.getCreated(), file.getNumberOfCalls()));
    }

    private void getFileByName() {
        String fileName = javaConsole.readLine("enter file name: ");
        printFile(fileManager.getByName(fileName));
    }

    private void getWithMaxCalls() {
        printFile(fileManager.getWithMaxCalls());
    }

    private void removeFilesBefore() {
        Date created;
        try {
            created = javaConsole.readDate();
            fileManager.removeFilesBefore(created);
        } catch (ParseException e) {
            javaConsole.printError("wrong date. please try again");
        }

    }
}
