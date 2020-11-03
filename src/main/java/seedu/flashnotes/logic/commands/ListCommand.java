package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Showing flashcards in deck. %1$d flashcards listed.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String currentDeckName = model.getCurrentDeckName();

        if (currentDeckName.equals(Model.getReservedDeckName())) {
            model.updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
        } else {
            TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(currentDeckName);
            model.updateFilteredFlashcardList(predicate);
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredFlashcardList().size()));
    }
}
