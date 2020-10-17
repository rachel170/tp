package seedu.flashnotes.model.deck;

import org.junit.jupiter.api.Test;
import seedu.flashnotes.model.deck.exceptions.DeckNotFoundException;
import seedu.flashnotes.model.deck.exceptions.DuplicateDeckException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.TypicalDecks.VALID_DECK_1;
import static seedu.flashnotes.testutil.TypicalDecks.VALID_DECK_2;

public class UniqueDeckListTest {

    private final UniqueDeckList uniqueDeckList = new UniqueDeckList();

    @Test
    public void contains_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.contains(null));
    }

    @Test
    public void contains_deckNotInList_returnsFalse() {
        assertFalse(uniqueDeckList.contains(VALID_DECK_1));
    }

    @Test
    public void contains_DeckInList_returnsTrue() {
        uniqueDeckList.add(VALID_DECK_2);
        assertTrue(uniqueDeckList.contains(VALID_DECK_2));
    }

    @Test
    public void add_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.add(null));
    }

    @Test
    public void add_duplicateDeck_throwsDuplicateDeckException() {
        uniqueDeckList.add(VALID_DECK_2);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList.add(VALID_DECK_2));
    }

    @Test
    public void setDeck_nullTargetDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDeck(null, VALID_DECK_2));
    }

    @Test
    public void setDeck_nullEditedDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDeck(VALID_DECK_2, null));
    }

    @Test
    public void setDeck_targetDeckNotInList_throwsDeckNotFoundException() {
        assertThrows(DeckNotFoundException.class, () -> uniqueDeckList.setDeck(
                VALID_DECK_2, VALID_DECK_2));
    }

    @Test
    public void setDeck_editedDeckIsSameDeck_success() {
        uniqueDeckList.add(VALID_DECK_2);
        uniqueDeckList.setDeck(VALID_DECK_2, VALID_DECK_2);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(VALID_DECK_2);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasSameDetails_success() {
        uniqueDeckList.add(VALID_DECK_2);
        Deck editedDeck = new Deck("Test Deck");
        uniqueDeckList.setDeck(VALID_DECK_2, editedDeck);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(editedDeck);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasDifferentDetails_success() {
        uniqueDeckList.add(VALID_DECK_1);
        uniqueDeckList.setDeck(VALID_DECK_1, VALID_DECK_2);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(VALID_DECK_2);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasNonUniqueDetails_throwsDuplicateDeckException() {
        uniqueDeckList.add(VALID_DECK_1);
        uniqueDeckList.add(VALID_DECK_2);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList.setDeck(VALID_DECK_1, VALID_DECK_2));
    }

    @Test
    public void remove_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.remove(null));
    }

    @Test
    public void remove_DeckDoesNotExist_throwsDeckNotFoundException() {
        assertThrows(DeckNotFoundException.class, () -> uniqueDeckList.remove(VALID_DECK_1));
    }

    @Test
    public void remove_existingDeck_removesDeck() {
        uniqueDeckList.add(VALID_DECK_2);
        uniqueDeckList.remove(VALID_DECK_2);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_nullUniqueDeckList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDecks((UniqueDeckList) null));
    }

    @Test
    public void setDeck_uniqueDeckList_replacesOwnListWithProvidedUniqueDeckList() {
        uniqueDeckList.add(VALID_DECK_1);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(VALID_DECK_2);
        uniqueDeckList.setDecks(expectedUniqueDeckList);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDecks((List<Deck>) null));
    }

    @Test
    public void setDecks_list_replacesOwnListWithProvidedList() {
        uniqueDeckList.add(VALID_DECK_1);
        List<Deck> deckList = Collections.singletonList(VALID_DECK_2);
        uniqueDeckList.setDecks(deckList);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(VALID_DECK_2);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_listWithDuplicateDecks_throwsDuplicateDeckException() {
        List<Deck> listWithDuplicateDecks = Arrays.asList(VALID_DECK_1, VALID_DECK_1);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList
                .setDecks(listWithDuplicateDecks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDeckList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void findDeck_listWithAvailableDeck_Success() {
        uniqueDeckList.add(VALID_DECK_1);
        Deck deckFound = uniqueDeckList.findDeck("Test Deck");
        Deck expectedDeck = new Deck("Test Deck");
        assertEquals(expectedDeck, deckFound);
    }

    @Test
    public void findDeck_listDoesNotHaveDeck_null() {
        Deck deckFound = uniqueDeckList.findDeck("Test Deck 2");
        assertEquals(null, deckFound);
    }
}
