package seedu.flashnotes.logic.commands;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.model.Model;

import static java.util.Objects.requireNonNull;

public class HomeCommand extends Command{
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the decks that are available in flashnotes\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_DECK_OVERVIEW, model.getFilteredDeckList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof HomeCommand; // instanceof handles nulls
    }
}
