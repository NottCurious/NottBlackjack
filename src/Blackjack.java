import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;

public class Blackjack {
    public static void main(String[] args) {
        try (Reader in = new InputStreamReader(System.in)) {
            Writer out = new OutputStreamWriter(System.out);
            UserIo userIo = new UserIo(in, out);
            Deck deck = new Deck(cards -> {
                userIo.println("RESHUFFLING");
                Collections.shuffle(cards);
                return cards;
            });
            Game game = new Game(deck, userIo);
            game.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
