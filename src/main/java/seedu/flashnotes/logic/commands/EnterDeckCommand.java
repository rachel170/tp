package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;


/**
 * Finds and lists all flashcards in flashnotes which has tags matching any of the argument keywords.
 * Tags of the flashcards correspond to the decks that they are in with a 1-1 relationship.
 * Keyword matching is case sensitive.
 */
public class EnterDeckCommand extends Command {

    public static final String COMMAND_WORD = "enterDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the flashcards that belong to specified deck\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " Singapore";

    public static final String MESSAGE_DECK_NOT_FOUND = "Deck does not exist in flashnotes";


    private final TagContainsKeywordsPredicate predicate;

    /**
     * Creates a command that shows all cards inside the deck created by the keyword of the predicate
     */
    public EnterDeckCommand(TagContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasDeck(new Deck(predicate.getKeyword()))) {
            throw new CommandException(MESSAGE_DECK_NOT_FOUND);
        }

        model.updateFilteredFlashcardList(predicate);
        model.setIsInDeckTrue();
        model.setCurrentDeckName(predicate.getKeyword());
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnterDeckCommand // instanceof handles nulls
                && predicate.equals(((EnterDeckCommand) other).predicate)); // state check
    }
}
