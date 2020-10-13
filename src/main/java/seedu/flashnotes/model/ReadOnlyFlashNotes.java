package seedu.flashnotes.model;

import javafx.collections.ObservableList;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Unmodifiable view of an flashnotes
 */
public interface ReadOnlyFlashNotes {

    /**
     * Returns an unmodifiable view of the flashcards list.
     * This list will not contain any duplicate flashcards.
     */
    ObservableList<Flashcard> getFlashcardList();

    /**
     * Returns an unmodifiable view of the deck list.
     * This list will not contain any duplicate decks.
     */
    ObservableList<Deck> getDeckList();
}
