package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_NO_CARDS_TO_REVIEW;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

public class ReviewCommandTest {
    private Model model;
    private Model expectedModel;

    @Test
    public void execute_review_success() throws Exception {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashNotes(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(ReviewCommand.SHOWING_REVIEW_MESSAGE,
                false, false, true, false, 0);
        expectedModel.setIsReviewModeTrue();
        expectedModel.shuffleReviewFlashcards();
        ReviewCommand reviewCommand = new ReviewCommand();
        assertEquals(reviewCommand.execute(model), expectedCommandResult);
        assertEquals(model.getFlashcardsToReview().size(), expectedModel.getFlashcardsToReview().size());
    }

    @Test
    public void execute_reviewEmptyDeck_throwsCommandException() throws Exception {
        model = new ModelManager();
        ReviewCommand reviewCommand = new ReviewCommand();
        assertThrows(CommandException.class, MESSAGE_NO_CARDS_TO_REVIEW, () -> reviewCommand.execute(model));
    }
}
