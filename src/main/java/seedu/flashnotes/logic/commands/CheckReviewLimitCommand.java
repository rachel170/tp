package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;



/**
 * Checks user preferences for maximum number of cards that can be reviewed per session.
 */
public class CheckReviewLimitCommand extends Command {
    public static final String COMMAND_WORD = "checkReviewLimit";
    public static final String MESSAGE_SUCCESS = "Review card limit is %d!";


    /**
     * Constructor to check review card limit
     */
    public CheckReviewLimitCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int reviewLimit = model.getReviewCardLimit();
        return new CommandResult(String.format(MESSAGE_SUCCESS, reviewLimit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetReviewLimitCommand // instanceof handles nulls
                );
    }
}

