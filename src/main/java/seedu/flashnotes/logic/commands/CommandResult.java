package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Start the review session **/
    private final boolean startReview;

    /** Determines whether a card has been flipped **/
    private final boolean isFlipped;

    /** Go to the next card in the review mode **/
    private final int isNext;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean startReview,
                         boolean isFlipped, int isNext) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.startReview = startReview;
        this.isFlipped = isFlipped;
        this.isNext = isNext;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, 0);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isStartReview() {
        return startReview;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public int isNext() {
        return isNext;

    public boolean isHome() {
        return feedbackToUser.startsWith("Back Home.");
    }

    public boolean isDeck() {
        return feedbackToUser.startsWith("Inside Deck.");
    }

    @Override
    public String toString() {
        return this.feedbackToUser;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
