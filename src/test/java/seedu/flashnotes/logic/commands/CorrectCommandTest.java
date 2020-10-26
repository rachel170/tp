package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.flashcard.Flashcard;

public class CorrectCommandTest {
    private Model model;

    @Test
    public void execute_correctCommandWhenFlashcardNotFlipped_success() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

        // To get a new flashcard
        model.updateFlashcardBeingReviewed(2);

        CommandResult expectedCommandResult = new CommandResult(CorrectCommand.MESSAGE_CORRECT_ERROR,
                false, false, false, false, 2);

        CorrectCommand correctCommand = new CorrectCommand();
        assertEquals(correctCommand.execute(model), expectedCommandResult);

        Flashcard flashcard = model.getFlashcardBeingReviewed();
        assertEquals(0, flashcard.getIsCorrect());
    }

    @Test
    public void execute_correctCommandWhenFlashcardFlipped_success() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

        // To get a new flashcard
        model.updateFlashcardBeingReviewed(2);

        CommandResult expectedCommandResult = new CommandResult(CorrectCommand.MESSAGE_CORRECT_ACKNOWLEDGEMENT,
                false, false, false, false, 2);

        CorrectCommand correctCommand = new CorrectCommand();
        Flashcard flashcard = model.getFlashcardBeingReviewed();
        model.carryOutFlipCommand();

        assertEquals(correctCommand.execute(model), expectedCommandResult);
        assertEquals(2, flashcard.getIsCorrect());
    }

}
