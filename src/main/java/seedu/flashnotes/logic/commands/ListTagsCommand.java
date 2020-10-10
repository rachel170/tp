package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;


/**
 * Finds and lists all flashcards in flashnotes which has tags matching any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "enterTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the flashcards that contain the keywords\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Singapore";


    private final TagContainsKeywordsPredicate predicate;

    public ListTagsCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTagsCommand // instanceof handles nulls
                && predicate.equals(((ListTagsCommand) other).predicate)); // state check
    }
}
