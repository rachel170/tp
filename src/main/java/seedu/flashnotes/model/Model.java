package seedu.flashnotes.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' flashnotes book file path.
     */
    Path getFlashNotesFilePath();

    /**
     * Sets the user prefs' flashnotes book file path.
     */
    void setFlashNotesFilePath(Path flashNotesFilePath);

    /**
     * Replaces flashnotes book data with the data in {@code flashNotes}.
     */
    void setFlashNotes(ReadOnlyFlashNotes flashNotes);

    /** Returns the FlashNotes */
    ReadOnlyFlashNotes getFlashNotes();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes book.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the flashnotes book.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the flashnotes book.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes book.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes book.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);
}
