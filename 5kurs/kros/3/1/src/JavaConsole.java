import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JavaConsole {

    private static final String DD_MM_YYYY = "dd/MM/yyyy";
    private Scanner scanner = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(msg);
    }

    public void printError(String msg) {
        print("ERROR: " + msg);
    }

    public String readLine(String fmt) {
        print(fmt);
        return scanner.nextLine();
    }

    public Integer readInteger(String fmt) {
        print(fmt);
        return Integer.parseInt(scanner.nextLine());
    }

    public Double readDouble(String fmt) {
        print(fmt);
        return Double.parseDouble(scanner.nextLine());
    }

    public Date readDate() throws ParseException {
        String dateAsString = readLine("put date in next format: " + DD_MM_YYYY);
        DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY);
        return formatter.parse(dateAsString);
    }

}
