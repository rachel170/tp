package seedu.flashnotes.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.flashnotes.testutil.FlashcardBuilder;

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
        Flashcard editedAlice = new FlashcardBuilder(WHAT).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Flashcard> newFlashcards = Arrays.asList(WHAT, editedAlice);
        FlashNotesStub newData = new FlashNotesStub(newFlashcards);

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
        Flashcard editedAlice = new FlashcardBuilder(WHAT).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(flashNotes.hasFlashcard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashNotes.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyFlashNotes whose flashcards list can violate interface constraints.
     */
    private static class FlashNotesStub implements ReadOnlyFlashNotes {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        FlashNotesStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }

        @Override
        public ObservableList<Deck> getDeckList() {
            return null;
            //TODO update later
        }
    }

}
