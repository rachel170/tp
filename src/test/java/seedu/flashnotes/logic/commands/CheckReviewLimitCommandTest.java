package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code CheckReviewLimitCommand}.
 */
public class CheckReviewLimitCommandTest {

    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void execute_success() {
        long validLimit = 20;
        String expectedMessage = String.format(CheckReviewLimitCommand.MESSAGE_SUCCESS, validLimit);

        ModelManager expectedModel = new ModelManager(model.getFlashNotes(), new UserPrefs());
        expectedModel.setReviewCardLimit(validLimit);
        model.setReviewCardLimit(validLimit);
        CheckReviewLimitCommand checkReviewLimitCommand = new CheckReviewLimitCommand();

        assertCommandSuccess(checkReviewLimitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        CheckReviewLimitCommand checkReviewLimitCommand = new CheckReviewLimitCommand();

        // same object -> returns true
        assertTrue(checkReviewLimitCommand.equals(checkReviewLimitCommand));

        // different types -> returns false
        assertFalse(checkReviewLimitCommand.equals(10));

        // null -> returns false
        assertFalse(checkReviewLimitCommand.equals(null));
    }
}

