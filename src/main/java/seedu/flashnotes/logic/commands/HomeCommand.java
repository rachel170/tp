package seedu.flashnotes.logic.commands;
import static java.util.Objects.requireNonNull;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.model.Model;

public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the decks that are available in flashnotes\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        model.setIsInDeckFalse();
        return new CommandResult(Messages.MESSAGE_DECK_OVERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof HomeCommand);
    }
}
