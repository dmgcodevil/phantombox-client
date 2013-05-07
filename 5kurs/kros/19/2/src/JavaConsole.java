import java.util.Scanner;

public class JavaConsole {

    private Scanner scanner = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(msg);
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

}
