package seedu.flashnotes.logic.commands;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_NO_CARDS_TO_REVIEW;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;

/*
 * Format full help instructions for every command for display.
 */
public class ReviewCommand extends Command {

    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a flashcard review session.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_REVIEW_MESSAGE = "Opened review window.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isReviewListEmpty = model.getFilteredFlashcardList().size() == 0;
        if (isReviewListEmpty) {
            throw new CommandException(MESSAGE_NO_CARDS_TO_REVIEW);
        }
        model.shuffleReviewFlashcards();
        model.setIsReviewModeTrue();
        return new CommandResult(SHOWING_REVIEW_MESSAGE, false, false, true, false, 0);
    }
}
