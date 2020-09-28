package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.Model;

/**
 * Clears the flashnotes book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFlashNotes(new FlashNotes());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
