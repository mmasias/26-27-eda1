
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {

    private final BufferedReader input;

    public Console() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readString() {
        return readString("");
    }

    public String readString(String message) {
        write(message);

        try {
            String text = input.readLine();

            if (text == null) {
                return "";
            }

            return text;
        } catch (Exception e) {
            return "";
        }
    }

    public char readChar() {
        return readChar("");
    }

    public char readChar(String message) {
        while (true) {
            String text = readString(message);

            if (text.length() == 1) {
                return text.charAt(0);
            }

            writeln("Introduce un solo caracter.");
        }
    }

    public int readInt() {
        return readInt("");
    }

    public int readInt(String message) {
        while (true) {
            String text = readString(message);

            try {
                return Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                writeln("Introduce un numero entero valido.");
            }
        }
    }

    public double readDouble() {
        return readDouble("");
    }

    public double readDouble(String message) {
        while (true) {
            String text = readString(message);

            try {
                return Double.parseDouble(text.trim());
            } catch (NumberFormatException e) {
                writeln("Introduce un numero decimal valido.");
            }
        }
    }

    public void write(String text) {
        System.out.print(text);
    }

    public void writeln(String text) {
        System.out.println(text);
    }

    public void writeln() {
        System.out.println();
    }

    public void write(char character) {
        System.out.print(character);
    }

    public void writeln(char character) {
        System.out.println(character);
    }

    public void write(int number) {
        System.out.print(number);
    }

    public void writeln(int number) {
        System.out.println(number);
    }

    public void write(double number) {
        System.out.print(number);
    }

    public void writeln(double number) {
        System.out.println(number);
    }

    public void write(Object object) {
        System.out.print(object);
    }

    public void writeln(Object object) {
        System.out.println(object);
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
