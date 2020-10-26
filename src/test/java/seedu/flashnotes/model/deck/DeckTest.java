package seedu.flashnotes.model.deck;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.testutil.TypicalDecks.VALID_DECK_1;
import static seedu.flashnotes.testutil.TypicalDecks.VALID_DECK_2;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    public void setDeckName_success() {
        VALID_DECK_1.setDeckName("new name");
        assertEquals("new name", VALID_DECK_1.getDeckName());
        VALID_DECK_1.setDeckName("Test Deck");
    }

    @Test
    public void setDeckName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VALID_DECK_1.setDeckName(null));
    }

    @Test
    public void setResultStatistic_success() {
        VALID_DECK_1.setResultStatistics("1");
        assertEquals("1", VALID_DECK_1.getResultStatistics());
    }

    @Test
    public void isValidDeckLength_success() {
        //incorrect input of empty deck name
        assertEquals(false, Deck.isValidDeckLength(""));
        //correct input of non-empty deck name
        assertEquals(true, Deck.isValidDeckLength("funky"));
    }

    @Test
    public void setResultStatistic_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> VALID_DECK_1.setResultStatistics(null));
    }

    @Test
    public void isSameDeck() {
        // same object -> returns true
        assertTrue(VALID_DECK_1.isSameDeck(VALID_DECK_1));

        // null -> returns false
        assertFalse(VALID_DECK_1.isSameDeck(null));

        // different name -> returns false
        assertFalse(VALID_DECK_1.isSameDeck(VALID_DECK_2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deck copyDeck = new Deck("Test Deck");
        assertTrue(VALID_DECK_1.equals(copyDeck));

        // same object -> returns true
        assertTrue(VALID_DECK_1.equals(VALID_DECK_1));

        // null -> returns false
        assertFalse(VALID_DECK_1.equals(null));

        // different type -> returns false
        assertFalse(VALID_DECK_1.equals(5));

        // different deck -> returns false
        assertFalse(VALID_DECK_1.equals(VALID_DECK_2));

        // different result statistic -> returns true
        Deck editedDeck = new Deck("Test Deck");
        editedDeck.setResultStatistics("1");
        assertTrue(VALID_DECK_1.equals(editedDeck));

        // change deck name -> returns false
        editedDeck = new Deck("funky");
        assertFalse(VALID_DECK_1.equals(editedDeck));
    }
}
