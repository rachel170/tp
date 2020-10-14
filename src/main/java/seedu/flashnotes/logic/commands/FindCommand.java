package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards in flashnotes which has question matching any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards which has questions contain any "
            + "of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " where";
    public static final String MESSAGE_SUCCESS = "%1$d flashcards found";

    private final QuestionContainsKeywordsPredicate predicate;

    public FindCommand(QuestionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String currentDeckName = model.getCurrentDeckName();
        model.updateFilteredFlashcardList(predicate.and(new TagContainsKeywordsPredicate(currentDeckName)));
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
