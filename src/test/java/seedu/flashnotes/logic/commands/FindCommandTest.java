package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.ReadOnlyFlashNotes;
import seedu.flashnotes.model.ReadOnlyUserPrefs;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.deck.UniqueDeckList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.QuestionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void equals() {
        QuestionContainsKeywordsPredicate firstPredicate =
                new QuestionContainsKeywordsPredicate(Collections.singletonList("first"));
        QuestionContainsKeywordsPredicate secondPredicate =
                new QuestionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    //    @Test
    //    public void execute_zeroKeywords_noFlashcardFound() {
    //        String expectedMessage = String.format(FindCommand.MESSAGE_SUCCESS, 0);
    //        QuestionContainsKeywordsPredicate predicate = preparePredicate(" ");
    //        FindCommand command = new FindCommand(predicate);
    //        Flashcard validFlashcard = new FlashcardBuilder().build();
    //        ModelStub modelStub = new ModelStubWithFlashcard(validFlashcard);
    //        assertThrows(CommandException.class,
    //                FindCommand.MESSAGE_USAGE, () -> command.execute(modelStub));
    //    }

    //    @Test
    //    public void execute_multipleKeywords_multipleFlashcardsFound() {
    //        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
    //        QuestionContainsKeywordsPredicate predicate = preparePredicate("why what when");
    //        FindCommand command = new FindCommand(predicate);
    //        expectedModel.updateFilteredFlashcardList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(WHAT, WHY, WHEN), model.getFilteredFlashcardList());
    //    }

    /**
     * Parses {@code userInput} into a {@code QuestionContainsKeywordsPredicate}.
     */
    private QuestionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new QuestionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFlashNotesFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashNotesFilePath(Path flashNotesFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void shuffleReviewFlashcards() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashNotes(ReadOnlyFlashNotes flashNotes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFlashNotes getFlashNotes() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Flashcard> getFlashcardsToReview() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Flashcard> addFlashcardToReview(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }
        public boolean hasDeck(Deck deck) {
            return false;
        }

        @Override
        public void deleteDeck(Deck target) {

        }

        @Override
        public void addDeck(Deck deck) {

        }

        @Override
        public void setDeck(Deck target, Deck editedDeck) {

        }

        @Override
        public boolean getIsInDeck() {
            return false;
        }

        @Override
        public void setIsInDeckTrue() {

        }

        @Override
        public void setIsInDeckFalse() {

        }

        @Override
        public void setCurrentDeckName(String deckName) {

        }

        @Override
        public String getCurrentDeckName() {
            return null;
        }

        @Override
        public ObservableList<Deck> getFilteredDeckList() {
            return null;
        }

        @Override
        public void updateFilteredDeckList(Predicate<Deck> predicate) {

        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFlashcard(Flashcard target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Flashcard> getFilteredFlashcardList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getIsReviewMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIsReviewModeFalse() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIsReviewModeTrue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Integer getReviewCardLimit() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReviewCardLimit(Integer reviewCardLimit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDeckPerformanceScore(Integer reviewScore, String deckName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UniqueDeckList getUniqueDeckList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single flashcard.
     */
    private class ModelStubWithFlashcard extends ModelStub {
        private final Flashcard flashcard;

        ModelStubWithFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            this.flashcard = flashcard;
        }

        @Override
        public boolean hasFlashcard(Flashcard flashcard) {
            requireNonNull(flashcard);
            return this.flashcard.isSameFlashcard(flashcard);
        }
    }
}
