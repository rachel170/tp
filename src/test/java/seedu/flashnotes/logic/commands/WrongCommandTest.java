package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.flashcard.Flashcard;

public class WrongCommandTest {
    private Model model;

    @Test
    public void execute_wrongCommandWhenFlashcardNotFlipped_success() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

        // To get a new flashcard
        model.updateFlashcardBeingReviewed(2);

        CommandResult expectedCommandResult = new CommandResult(WrongCommand.MESSAGE_WRONG_ERROR,
                false, false, false, false, 1);

        WrongCommand wrongCommand = new WrongCommand();
        assertEquals(wrongCommand.execute(model), expectedCommandResult);

        Flashcard flashcard = model.getFlashcardBeingReviewed();
        assertEquals(1, flashcard.getIsCorrect());
    }

    @Test
    public void execute_wrongCommandWhenFlashcardFlipped_success() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

        // To get a new flashcard
        model.updateFlashcardBeingReviewed(2);

        CommandResult expectedCommandResult = new CommandResult(WrongCommand.MESSAGE_WRONG_ACKNOWLEDGEMENT,
                false, false, false, false, 1);

        WrongCommand wrongCommand = new WrongCommand();
        Flashcard flashcard = model.getFlashcardBeingReviewed();
        model.carryOutFlipCommand();

        assertEquals(wrongCommand.execute(model), expectedCommandResult);
        assertEquals(1, flashcard.getIsCorrect());
    }


}
