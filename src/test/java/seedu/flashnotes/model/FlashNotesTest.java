package seedu.flashnotes.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.TypicalDecks.VALID_DECK_1;
import static seedu.flashnotes.testutil.TypicalDecks.VALID_DECK_2;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHY;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.deck.exceptions.DeckNotFoundException;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.flashnotes.testutil.FlashcardBuilder;
import seedu.flashnotes.testutil.TypicalDecks;

public class FlashNotesTest {

    private final FlashNotes flashNotes = new FlashNotes();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), flashNotes.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashNotes.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFlashNotes_replacesData() {
        FlashNotes newData = getTypicalFlashNotes();
        flashNotes.resetData(newData);
        assertEquals(newData, flashNotes);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardException() {
        // Two flashcards with the same identity fields
        Flashcard editedAlice = new FlashcardBuilder(WHAT).build();
        List<Flashcard> newFlashcards = Arrays.asList(WHAT, editedAlice);
        List<Deck> newDecks = TypicalDecks.getTypicalDecks();
        FlashNotesStub newData = new FlashNotesStub(newFlashcards, newDecks);

        assertThrows(DuplicateFlashcardException.class, () -> flashNotes.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashNotes.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashNotes_returnsFalse() {
        assertFalse(flashNotes.hasFlashcard(WHAT));
    }

    @Test
    public void hasFlashcard_flashcardInFlashNotes_returnsTrue() {
        flashNotes.addFlashcard(WHAT);
        assertTrue(flashNotes.hasFlashcard(WHAT));
    }

    @Test
    public void hasFlashcard_flashcardWithSameDetailFieldsInFlashNotes_returnsTrue() {
        flashNotes.addFlashcard(WHAT);
        Flashcard editedAlice = new FlashcardBuilder(WHAT)
                .build();
        assertTrue(flashNotes.hasFlashcard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashNotes.getFlashcardList().remove(0));
    }

    @Test
    public void setFlashcard_flashcardWithSameDetailFieldsInFlashNotes_returnsTrue() {
        flashNotes.addFlashcard(WHAT);
        Flashcard editedAlice = new FlashcardBuilder(WHY)
                .build();
        flashNotes.setFlashcard(WHAT, editedAlice);

        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(editedAlice);
        FlashNotesStub flashNotesStub = new FlashNotesStub(flashcards, new ArrayList<>());
        assertEquals(flashNotesStub.getFlashcardList(), flashNotes.getFlashcardList());
    }

    @Test
    public void setFlashcard_flashcardWithDifferentDetailFieldsInFlashNotes_fails() {
        flashNotes.addFlashcard(WHAT);
        Flashcard editedAlice = new FlashcardBuilder(WHY)
                .build();
        flashNotes.setFlashcard(WHAT, editedAlice);

        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(WHAT);
        FlashNotesStub flashNotesStub = new FlashNotesStub(flashcards, new ArrayList<>());
        assertNotEquals(flashNotesStub.getFlashcardList(), flashNotes.getFlashcardList());
    }

    @Test
    public void hasDeck_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashNotes.hasDeck(null));
    }

    @Test
    public void hasDeck_deckNotInFlashNotes_returnsFalse() {
        assertFalse(flashNotes.hasDeck(new Deck("WHAT")));
    }

    @Test
    public void hasDeck_deckInFlashNotes_returnsTrue() {
        flashNotes.addDeck(VALID_DECK_1);
        assertTrue(flashNotes.hasDeck(VALID_DECK_1));
    }

    @Test
    public void getDeckList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashNotes.getDeckList().remove(0));
    }

    @Test
    public void setDeck_deckWithSameDeckNameInFlashNotes_returnsTrue() {
        flashNotes.addDeck(VALID_DECK_1);
        Deck theDeck = new Deck ("Test Deck");
        flashNotes.setDeck(VALID_DECK_1, theDeck);

        List<Deck> decks = new ArrayList<>();
        decks.add(theDeck);
        FlashNotesStub flashNotesStub = new FlashNotesStub(new ArrayList<>(), decks);
        assertEquals(flashNotesStub.getDeckList(), flashNotes.getDeckList());
    }

    @Test
    public void setDeck_deckWithDifferentDetailFieldsInFlashNotes_notEqual() {
        flashNotes.addDeck(VALID_DECK_1);
        flashNotes.setDeck(VALID_DECK_1, VALID_DECK_2);

        List<Deck> decks = new ArrayList<>();
        decks.add(VALID_DECK_1);
        FlashNotesStub flashNotesStub = new FlashNotesStub(new ArrayList<>(), decks);
        assertNotEquals(flashNotesStub.getDeckList(), flashNotes.getDeckList());
    }

    @Test
    public void removeDeck_deckFoundInNonEmptyFlashNotes_success() {
        flashNotes.setDecks(TypicalDecks.getTypicalDecks());
        flashNotes.removeDeck(VALID_DECK_1);
        flashNotes.removeDeck(VALID_DECK_2);
        List<Deck> decks = new ArrayList<>();
        FlashNotesStub flashNotesStub = new FlashNotesStub(new ArrayList<>(), decks);
        assertEquals(flashNotesStub.getDeckList(), flashNotes.getDeckList());
    }

    @Test
    public void removeDeck_deckNotFoundInNonEmptyFlashNotes_throwsDeckNotFoundException() {
        flashNotes.setDecks(TypicalDecks.getTypicalDecks());
        assertThrows(DeckNotFoundException.class, () -> flashNotes.removeDeck(new Deck ("Funky")));
    }

    /**
     * A stub ReadOnlyFlashNotes whose flashcards list can violate interface constraints.
     */
    private static class FlashNotesStub implements ReadOnlyFlashNotes {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();
        private final ObservableList<Deck> decks = FXCollections.observableArrayList();

        FlashNotesStub(Collection<Flashcard> flashcards, Collection<Deck> decks) {
            this.flashcards.setAll(flashcards);
            this.decks.setAll(decks);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }

        @Override
        public ObservableList<Deck> getDeckList() {
            return decks;
        }
    }

}
