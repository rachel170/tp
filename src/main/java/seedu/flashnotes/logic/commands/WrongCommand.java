package seedu.flashnotes.logic.commands;

import seedu.flashnotes.model.Model;

/**
 * Marks the flashcard being reviewed as wrong.
 */

public class WrongCommand extends Command {
    public static final String COMMAND_WORD = "w";

    public static final String MESSAGE_WRONG_ACKNOWLEDGEMENT = "Marking Flashcard as wrong as requested...";

    @Override
    public CommandResult execute(Model model) {
        if (model.getIsFlashcardFlipped()) {
            model.updateFlashcardBeingReviewed(1);
        }
        return new CommandResult(MESSAGE_WRONG_ACKNOWLEDGEMENT, false, false, true, false, 1);
    }
}
