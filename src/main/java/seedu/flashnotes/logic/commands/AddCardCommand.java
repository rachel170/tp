package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.tag.Tag;

/**
 * Adds a flashcard to the flashnotes.
 */
public class AddCardCommand extends Command {

    public static final String COMMAND_WORD = "addCard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to the flashnotes. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "Why is the sky blue? "
            + PREFIX_ANSWER + "Because it's a reflection of the sea";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the flashnotes";

    private final Flashcard toAdd;
    private String additionalMessage = "";

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AddCardCommand(Flashcard flashcard) {
        requireNonNull(flashcard);
        toAdd = flashcard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashcard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }
        Tag tag = toAdd.getTag();
        if (!model.hasDeck(new Deck(tag.tagName))) {
            model.addDeck(new Deck(tag.tagName));
        }

        model.addFlashcard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd) + additionalMessage);
    }

    /**
     * Adds an additional message behind the feedback for Command Result.
     *
     * @param additionalMessage given by the caller.
     */
    public void setAdditionalMessage(String additionalMessage) {
        requireNonNull(additionalMessage);
        this.additionalMessage = additionalMessage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCardCommand // instanceof handles nulls
                && toAdd.equals(((AddCardCommand) other).toAdd));
    }
}
