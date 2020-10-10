package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.model.Model;

/**
 * Lists all flashcards in the flashnotes to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all flashcards";

    public static final String DECK_NAME = "Default";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        model.setIsInDeckTrue();
        model.setCurrentDeckName(DECK_NAME);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW
                        + " " + MESSAGE_SUCCESS,
                        model.getFilteredFlashcardList().size()));
    }
}
