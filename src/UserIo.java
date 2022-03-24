import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * Class is responsible for printing output to the screen and reading input from the user.
 */
public class UserIo {
    private BufferedReader in;
    private PrintWriter out;

    public UserIo(Reader in, Writer out) {
        this.in = new BufferedReader(in);
        this.out = new PrintWriter(out, true);
    }

    /**
     * Print the line of text to output including a trailing \n
     *
     * @param text the text to print
     */
    public void println(String text) {
        out.println(text);
    }

    /**
     * Print the given text with left padded with spaces.
     *
     * @param text the text to print.
     * @param leftPad the number of spaces to pad the left side with.
     */
    public void println(String text, int leftPad) {
        IntStream.range(0, leftPad).forEach((i) -> out.print(' '));
        out.println(text);
    }

    /**
     * Print the given text <i>without</i> a line break
     *
     * @param text the text to print.
     */
    public void print(String text) {
        out.print(text);
        out.flush();
    }

    /**
     * Reads a line from input
     *
     * @return The line entered into input
     * @throws UncheckedIOException if the line is null (CTRL+D or CTRL+Z)
     */
    private String readLine() {
        try {
            String line = in.readLine();
            if (line == null) {
                throw new UncheckedIOException("!!END OF INPUT", new EOFException());
            }
            return line;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Prompt the user via input
     *
     * @param prompt the text to display to the user.
     * @return the line read from input.
     */
    public String prompt(String prompt) {
        print(prompt + "? ");
        return readLine();
    }

    /**
     * Prompts the user for a "Yes" or "No" answer.
     *
     * @param prompt The prompt to display.
     * @return false if the user enters No.
     */
    public boolean promptBoolean(String prompt) {
        print(prompt + "? ");
        String input = readLine();

        if(input.toLowerCase().startsWith("n")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Prompts the user for an integer
     *
     * @param prompt the prompt to display
     * @return the number entered.
     */
    public int promptInt(String prompt) {
        print(prompt + "? ");

        while(true) {
            String input = readLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                println("NUMBER EXPECTED");
                print("? ");
            }
        }
    }

    /**
     * Prompts the user for a double
     *
     * @param prompt the prompt for the user
     * @return the double the user enters.
     */
    public double promptDouble(String prompt) {
        print(prompt + "? ");

        while(true) {
            String input = readLine();

            try {
                Double.parseDouble(input);
            } catch (NumberFormatException e) {
                println("DOUBLE EXPECTED");
                print("? ");
            }
        }
    }


}
