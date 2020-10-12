package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.HOW;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHO;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHY;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

public class EnterDeckCommandTest {
    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate("first");
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate("second");

        EnterDeckCommand listTagsFirstCommand = new EnterDeckCommand(firstPredicate);
        EnterDeckCommand listTagsSecondCommand = new EnterDeckCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listTagsFirstCommand.equals(listTagsFirstCommand));

        // same values -> returns true
        EnterDeckCommand listTagsFirstCommandCopy = new EnterDeckCommand(firstPredicate);
        assertTrue(listTagsFirstCommand.equals(listTagsFirstCommandCopy));

        // different types -> returns false
        assertFalse(listTagsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listTagsFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(listTagsFirstCommand.equals(listTagsSecondCommand));
    }

//    @Test
//    public void execute_zeroKeywords_noFlashcardFound() {
//        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
//        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
//        EnterDeckCommand command = new EnterDeckCommand(predicate);
//        expectedModel.updateFilteredFlashcardList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
//    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends");
        EnterDeckCommand command = new EnterDeckCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(WHO, WHY, HOW), model.getFilteredFlashcardList());
    }

    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(userInput);
    }
}
