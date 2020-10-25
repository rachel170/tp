package seedu.flashnotes.logic.commands;

import seedu.flashnotes.model.Model;

/**
 * Terminates the program.
 */
public class EndReviewCommand extends Command {

    public static final String COMMAND_WORD = "endReview";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Review Session as requested ...";

    @Override
    public CommandResult execute(Model model) {
        // Turn review mode off in model
        model.setIsReviewModeFalse();
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false, 0);
    }

}
