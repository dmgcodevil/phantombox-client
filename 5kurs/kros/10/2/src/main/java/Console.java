

public interface Console {

    void print(String msg);

    String readLine(String msg);

    String readLineAndValidate(String msg);

    /**
     * Better use readIntAndValidate instead.
     *
     * @throws NumberFormatException
     */
    Integer readInt(String msg);

    Integer readIntAndValidate(String msg);
}
