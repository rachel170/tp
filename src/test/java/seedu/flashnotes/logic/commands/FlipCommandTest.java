package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.flashcard.Flashcard;

public class FlipCommandTest {
    private Model model;

    @Test
    public void execute_flipCommandResult_success() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(FlipCommand.MESSAGE_FLIP_ACKNOWLEDGEMENT,
                false, false, false, true, 0);

        Flashcard flashcard = model.getFlashcardBeingReviewed();

        FlipCommand flipCommand = new FlipCommand();
        assertEquals(expectedCommandResult, flipCommand.execute(model));

        assertEquals(true, flashcard.getIsFlipped());
    }

    @Test
    public void execute_doubleFlipCommandResult_success() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(FlipCommand.MESSAGE_FLIP_ACKNOWLEDGEMENT,
                false, false, false, true, 0);

        Flashcard flashcard = model.getFlashcardBeingReviewed();

        FlipCommand flipCommand = new FlipCommand();
        CommandResult firstResult = flipCommand.execute(model);
        assertEquals(expectedCommandResult, firstResult);

        assertEquals(true, flashcard.getIsFlipped());

        assertEquals(expectedCommandResult, flipCommand.execute(model));

        assertEquals(false, flashcard.getIsFlipped());
    }
}
