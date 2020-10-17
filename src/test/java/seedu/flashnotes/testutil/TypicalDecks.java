package seedu.flashnotes.testutil;

import seedu.flashnotes.model.deck.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Deck} objects to be used in tests.
 */
public class TypicalDecks {
    public static final Deck VALID_DECK_1 = new Deck("Test Deck");
    public static final Deck VALID_DECK_2 = new Deck ("Test Deck 2");

    private TypicalDecks() {} // prevents instantiation

    public static List<Deck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(VALID_DECK_2, VALID_DECK_1));
    }
}
