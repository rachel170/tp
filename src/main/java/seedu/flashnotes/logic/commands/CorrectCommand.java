package seedu.flashnotes.logic.commands;

import seedu.flashnotes.model.Model;

/**
 * Marks the flashcard being reviewed as correct.
 */
public class CorrectCommand extends Command {
    public static final String COMMAND_WORD = "c";

    public static final String MESSAGE_CORRECT_ACKNOWLEDGEMENT = "Marking Flashcard as correct as requested...";
    public static final String MESSAGE_CORRECT_ERROR = "Need to flip card before marking it as correct or wrong.";

    @Override
    public CommandResult execute(Model model) {
        if (model.getIsFlashcardFlipped()) {
            model.updateFlashcardBeingReviewed(2);
        } else {
            return new CommandResult(MESSAGE_CORRECT_ERROR, false, false,
                    true, false, 0);
        }

        return new CommandResult(MESSAGE_CORRECT_ACKNOWLEDGEMENT, false, false,
                true, false, 2);
    }
}
