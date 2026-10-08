import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Consola {

    private static final String INTEGER_regExp = "-?\\d+";
    private static final String DOUBLE_regExp = "-?(\\d+(\\.\\d+)?([eE][+-]?\\d+)?|\\.\\d+([eE][+-]?\\d+)?)";
    private static final String CHAR_regExp = ".";

    private BufferedReader input;

    public Consola() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readString() {
        return readString("");
    }

    public String readString(String title) {
        assert title != null;

        write(title);

        String string = "";

        try {
            string = input.readLine();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return string;
    }

    public char readChar() {
        return readChar("");
    }

    public char readChar(String title) {
        assert title != null;

        Pattern charPattern = Pattern.compile(CHAR_regExp);
        char characterInput = ' ';
        boolean ok;

        do {
            String string = readString(title);
            ok = charPattern.matcher(string).find();

            if (ok) {
                characterInput = string.charAt(0);
            } else {
                writeError(charPattern.toString());
            }

        } while (!ok);

        return characterInput;
    }

    public int readInt() {
        return readInt("");
    }

    public int readInt(String title) {
        assert title != null;

        Pattern intPattern = Pattern.compile(INTEGER_regExp);
        int intInput = 0;
        boolean ok;

        do {
            String string = readString(title);
            ok = intPattern.matcher(string.trim()).matches();

            if (ok) {
                intInput = Integer.parseInt(string.trim());
            } else {
                writeError(intPattern.toString());
            }

        } while (!ok);

        return intInput;
    }

    public double readDouble() {
        return readDouble("");
    }

    public double readDouble(String title) {
        assert title != null;

        Pattern doublePattern = Pattern.compile(DOUBLE_regExp);
        double doubleInput = 0;
        boolean ok;

        do {
            String string = readString(title);
            ok = doublePattern.matcher(string.trim()).matches();

            if (ok) {
                doubleInput = Double.parseDouble(string.trim());
            } else {
                writeError(doublePattern.toString());
            }

        } while (!ok);

        return doubleInput;
    }

    public void write(String string) {
        assert string != null;

        System.out.print(string);
    }

    public void writeln(String string) {
        write(string + "\n");
    }

    public void writeln() {
        writeln("");
    }

    public void write(char character) {
        System.out.print(character);
    }

    public void writeln(char character) {
        write(character + "\n");
    }

    public void write(int value) {
        System.out.print(value);
    }

    public void writeln(int value) {
        write(value + "\n");
    }

    public void write(double value) {
        System.out.print(value);
    }

    public void writeln(double value) {
        write(value + "\n");
    }

    public void write(Object object) {
        System.out.print(object);
    }

    public void writeln(Object object) {
        write(object + "\n");
    }

    private void writeError(String regExp) {
        System.out.println("Error de formato: se esperaba " + regExp);
    }

    public void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException exception) {
        }
    }

    public void pause(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException exception) {
        }
    }
}