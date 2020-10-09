package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.deck.UniqueDeckList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.UniqueFlashcardList;
import seedu.flashnotes.model.tag.Tag;

/**
 * Wraps all data at the flashnotes level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class FlashNotes implements ReadOnlyFlashNotes {

    private final UniqueFlashcardList flashcards;
    private final UniqueDeckList decks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
        decks = new UniqueDeckList();
    }

    public FlashNotes() {}

    /**
     * Creates an FlashNotes using the Flashcards in the {@code toBeCopied}
     */
    public FlashNotes(ReadOnlyFlashNotes toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Replaces the contents of the deck list with {@code decks}.
     * {@code decks} must not contain duplicate decks.
     */
    public void setDecks(List<Deck> decks) {
        this.decks.setDecks(decks);
    }

    /**
     * Resets the existing data of this {@code FlashNotes} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashNotes newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());


        //TODO eventually to be changed to read directly from list - PX
        List<Deck> newDeckData = new ArrayList<>();
        List<String> uniqueDeckNames = new ArrayList<>();
        uniqueDeckNames.add("Default");
        List<String> finalDeckNames = new ArrayList<>();
        finalDeckNames.add("Default");
        for (Flashcard card : newData.getFlashcardList()) {
            Set<Tag> tags = card.getTags();
            for (Tag tag: tags) {
                for (String deckName: uniqueDeckNames) {
                    if (!tag.tagName.equals(deckName)) {
                        finalDeckNames.add(tag.tagName);
                    }
                }
            }
        }
        for (String s : uniqueDeckNames) {
            Deck newDeck = new Deck(s);
            newDeckData.add(newDeck);
        }
        setDecks(newDeckData);
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the flashnotes.
     * The flashcard must not already exist in the flashnotes.
     */
    public void addFlashcard(Flashcard card) {
        flashcards.add(card);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashNotes}.
     * {@code key} must exist in the flashnotes.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }


    //// Deck-level operations

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the flashnotes.
     */
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    /**
     * Adds a deck to the flashnotes.
     * The deck must not already exist in the flashnotes.
     */
    public void addDeck(Deck card) {
        decks.add(card);
    }

    /**
     * Replaces the given deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist in the flashnotes.
     * The deck identity of {@code editedDeck} must not be the same
     * as another existing deck in the flashnotes.
     */
    public void setDeck(Deck target, Deck editedDeck) {
        requireNonNull(editedDeck);

        decks.setDeck(target, editedDeck);
    }

    /**
     * Removes {@code keyDeck} from this {@code FlashNotes}.
     * {@code keyDeck} must exist in the flashnotes.
     */
    public void removeDeck(Deck keyDeck) {
        decks.remove(keyDeck);
    }
    //// util methods

    @Override
    public String toString() {
        //return flashcards.asUnmodifiableObservableList().size() + " flashcards";
        // TODO: refine later
        return flashcards.asUnmodifiableObservableList().toString();
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Deck> getDeckList() {
        //todo read the tags and update
        //todo change when we have decklist implementation up
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashNotes // instanceof handles nulls
                && flashcards.equals(((FlashNotes) other).flashcards))
                && decks.equals(((FlashNotes) other).decks);
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
