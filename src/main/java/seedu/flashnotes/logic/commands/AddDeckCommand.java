package seedu.flashnotes.logic.commands;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_DECK_NAME;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;

public class AddDeckCommand extends Command{
    public static final String COMMAND_WORD = "addDeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Deck to flashnotes. "
            + "Parameters: "
            + PREFIX_DECK_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DECK_NAME + "CNM1046 ";

    public static final String MESSAGE_SUCCESS = "New Deck added: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck already exists in the flashnotes";
    private final Deck toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AddDeckCommand(Deck cardDeck) {
        requireNonNull(cardDeck);
        toAdd = cardDeck;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDeck(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }
        //todo change the dummy initialisation of flashcards - PX
        Set<Tag> set = new HashSet<>();
        set.add(new Tag(toAdd.getDeckName()));
        model.addFlashcard(new Flashcard(new Question("dummy qn"), new Answer("dummy answer"), set));
        model.addDeck(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
