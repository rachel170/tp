package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Represents the in-memory model of the flashnotes data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FlashNotes flashNotes;
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;
    private FilteredList<Flashcard> flashcardsToReview;

    /**
     * Initializes a ModelManager with the given flashNotes and userPrefs.
     */
    public ModelManager(ReadOnlyFlashNotes flashNotes, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(flashNotes, userPrefs);

        logger.fine("Initializing with flashnotes: " + flashNotes + " and user prefs " + userPrefs);

        this.flashNotes = new FlashNotes(flashNotes);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.flashNotes.getFlashcardList());
        flashcardsToReview = new FilteredList<>(this.flashNotes.getFlashcardList());
    }

    public ModelManager() {
        this(new FlashNotes(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFlashNotesFilePath() {
        return userPrefs.getFlashNotesFilePath();
    }

    @Override
    public void setFlashNotesFilePath(Path flashNotesFilePath) {
        requireNonNull(flashNotesFilePath);
        userPrefs.setFlashNotesFilePath(flashNotesFilePath);
    }

    @Override
    public Integer getReviewCardLimit() {
        return userPrefs.getReviewCardLimit();
    }

    @Override
    public void setReviewCardLimit(Integer reviewCardLimit) {
        requireNonNull(reviewCardLimit);
        userPrefs.setReviewCardLimit(reviewCardLimit);
    }

    //=========== FlashNotes ================================================================================

    @Override
    public void setFlashNotes(ReadOnlyFlashNotes flashNotes) {
        this.flashNotes.resetData(flashNotes);
    }

    @Override
    public ReadOnlyFlashNotes getFlashNotes() {
        return flashNotes;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashNotes.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        flashNotes.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        flashNotes.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);

        flashNotes.setFlashcard(target, editedFlashcard);
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} backed by the internal list of
     * {@code versionedFlashNotes}
     */
    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return filteredFlashcards;
    }

    @Override
    public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
        requireNonNull(predicate);
        filteredFlashcards.setPredicate(predicate);
    }

    //=========== Shuffled Flashcard List Operations =============================================================

    /**
     * Shuffles and trims the list of flashcards to review.
     */
    public void shuffleReviewFlashcards() {
        // apply shuffling algorithm
        ObservableList<Flashcard> flashcardsToReviewList = FXCollections.observableArrayList(
                this.flashNotes.getFlashcardList());
        FXCollections.shuffle(flashcardsToReviewList);


        // trim using user preferences
        Integer reviewCardLimit = userPrefs.getReviewCardLimit();
        if (reviewCardLimit < flashcardsToReviewList.size()) {
            flashcardsToReviewList = FXCollections.observableArrayList(
                    flashcardsToReviewList.subList(0, reviewCardLimit));
        }

        // store shuffled and trimmed result into this.flashcardsToReview
        this.flashcardsToReview = new FilteredList<>(flashcardsToReviewList);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} to review.
     */
    @Override
    public ObservableList<Flashcard> getFlashcardsToReview() {
        return flashcardsToReview;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return flashNotes.equals(other.flashNotes)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

}
