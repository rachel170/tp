package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.LogicManager;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.deck.Deck;

/**
 * Deletes a deck and all its corresponding cards from FlashNotes.
 */
public class DeleteDeckCommand extends Command {
    public static final String COMMAND_WORD = "deleteDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a Deck from flashnotes. "
            + "Parameters: "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 ";

    public static final String MESSAGE_DELETE_DECK_SUCCESS = "Deleted deck: %1$s";

    private final Logger logger = LogsCenter.getLogger(DeleteDeckCommand.class);

    private final Index targetIndex;




    /**
     * Creates a delete deck command that deletes the specified deck and all cards in the deck.
     */
    public DeleteDeckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Deck> lastShownList = model.getFilteredDeckList();
        logger.info("List of size " + lastShownList.size() + " retrieved");

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToDelete = lastShownList.get(targetIndex.getZeroBased());

        assert deckToDelete != null : "Deck should exist";
        model.deleteDeck(deckToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_DECK_SUCCESS, deckToDelete.getDeckName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDeckCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDeckCommand) other).targetIndex)); // state check
    }
}
