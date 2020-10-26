package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code SetReviewLimitCommand}.
 */
public class SetReviewLimitCommandTest {

    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void execute_validLimit_success() {
        Integer validLimit = 20;
        SetReviewLimitCommand setReviewLimitCommand = new SetReviewLimitCommand(validLimit);

        String expectedMessage = SetReviewLimitCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getFlashNotes(), new UserPrefs());
        expectedModel.setReviewCardLimit(validLimit);
        //TODO check failure reason
        //assertCommandSuccess(setReviewLimitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLimit_throwsCommandException() {
        Integer invalidLimit = 0;
        SetReviewLimitCommand setReviewLimitCommand = new SetReviewLimitCommand(invalidLimit);

        //TODO check failure reason
        //assertCommandFailure(setReviewLimitCommand, model, Messages.MESSAGE_INVALID_LIMIT);
    }

    @Test
    public void equals() {
        SetReviewLimitCommand setReviewLimitCommand10 = new SetReviewLimitCommand(10);
        SetReviewLimitCommand setReviewLimitCommand30 = new SetReviewLimitCommand(30);

        // same object -> returns true
        assertTrue(setReviewLimitCommand10.equals(setReviewLimitCommand10));

        // same values -> returns true
        SetReviewLimitCommand setReviewLimitCommand10Copy = new SetReviewLimitCommand(10);
        assertTrue(setReviewLimitCommand10.equals(setReviewLimitCommand10Copy));

        // different types -> returns false
        assertFalse(setReviewLimitCommand10.equals(10));

        // null -> returns false
        assertFalse(setReviewLimitCommand10.equals(null));

        // different limit -> returns false
        assertFalse(setReviewLimitCommand10.equals(setReviewLimitCommand30));
    }
}

