package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;



/**
 * Sets user preferences for maximum number of cards that can be reviewed per session.
 */
public class SetReviewLimitCommand extends Command {
    public static final String COMMAND_WORD = "set-review-limit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets user preferences for maximum number of cards that can "
            + "be reviewed per session.\n"
            + "Parameters: NUMBER\n"
            + "Example: " + COMMAND_WORD + " 20";
    public static final String MESSAGE_SUCCESS = "Review card limit successfully updated!";
    public static final String MESSAGE_INVALID_LIMIT = "Review card limit must be an integer greater than 0.";

    private final Integer reviewCardLimit;

    /**
     * Constructor to set review card limit
     * @param reviewCardLimit
     */
    public SetReviewLimitCommand(Integer reviewCardLimit) {
        requireNonNull(reviewCardLimit);
        this.reviewCardLimit = reviewCardLimit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (reviewCardLimit < 1) {
            throw new CommandException(MESSAGE_INVALID_LIMIT);
        }

        model.setReviewCardLimit(reviewCardLimit);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetReviewLimitCommand // instanceof handles nulls
                && reviewCardLimit.equals(((SetReviewLimitCommand) other).reviewCardLimit));
    }
}
