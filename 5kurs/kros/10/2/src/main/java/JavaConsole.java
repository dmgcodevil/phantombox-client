import java.util.Scanner;

public class JavaConsole implements Console {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ERROR_NOT_NUMBER = "Error. Please enter a number.";
    private static final String ERROR_EMPTY_STRING = "Error. You can't enter empty string.";

    public JavaConsole() {
        if (SCANNER == null) {
            throw new IllegalArgumentException("error create scanner.");
        }
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readLine(String msg) {
        print(msg);
        return SCANNER.nextLine();
    }

    @Override
    public String readLineAndValidate(String msg) {
        String val;
        boolean valid = false;
        do {
            val = readLine(msg);
            if (!isNotEmpty(val)) {
                print(ERROR_EMPTY_STRING);
            } else {
                valid = true;
            }
        } while (!valid);

        return val;
    }

    @Override
    public Integer readInt(String msg) {
        print(msg);
        return SCANNER.nextInt();
    }

    @Override
    public Integer readIntAndValidate(String msg) {
        String val;
        boolean valid = false;
        do {
            val = readLine(msg);
            if (!isNumeric(val)) {
                print(ERROR_NOT_NUMBER);
            } else {
                valid = true;
            }
        } while (!valid);

        return Integer.parseInt(val);
    }

    private static boolean isNotEmpty(String val) {
        return val != null && val.length() > 0 ? true : false;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
