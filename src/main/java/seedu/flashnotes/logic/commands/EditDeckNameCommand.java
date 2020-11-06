package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_NEW_DECK_NAME;

import java.util.List;
import java.util.logging.Logger;

import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

/**
 * Edits the deck name and tags of corresponding cards.
 */
public class EditDeckNameCommand extends Command {

    public static final String COMMAND_WORD = "editDeckName";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the deck name and "
            + "the tag of all corresponding cards to the specified keyword"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_NEW_DECK_NAME + "NEW DECK NAME \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NEW_DECK_NAME + "History ";

    public static final String MESSAGE_SUCCESS = "Deck edited successfully: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "The deck name that you are trying to use already exists. "
            + "Please enter a new deck name.";

    private final Logger logger = LogsCenter.getLogger(EditDeckNameCommand.class);

    private final Index index;
    private final Deck newDeck;


    /**
     * @param index of deck to be edited.
     * @param newDeck details of new deck.
     */
    public EditDeckNameCommand(Index index, Deck newDeck) {
        requireNonNull(index);
        requireNonNull(newDeck);

        this.index = index;
        this.newDeck = newDeck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Deck> lastShownList = model.getFilteredDeckList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        if (model.hasDeck(newDeck)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        // Transfer Results Statistics from old deck to new deck
        Deck deckToEdit = lastShownList.get(index.getZeroBased());
        String resultStatistics = deckToEdit.getResultStatistics();
        newDeck.setResultStatistics(resultStatistics);
        model.setDeck(deckToEdit, newDeck);
        logger.info("Old Deck: " + deckToEdit.getDeckName() + " with stats: " + deckToEdit.getResultStatistics()
                + ". New Deck: " + newDeck.getDeckName() + " with stats: " + newDeck.getResultStatistics());


        changeTagOfCards(deckToEdit.getDeckName(), newDeck.getDeckName(), model);
        model.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newDeck.getDeckName()));

    }


    /**
     * Updates the tags of the cards with the new deck name
     */
    private void changeTagOfCards(String deckName, String newDeckName, Model model) {
        model.updateFilteredFlashcardList(new TagContainsKeywordsPredicate(deckName));
        List<Flashcard> cardsWithTag = model.getFilteredFlashcardList();
        if (cardsWithTag.size() > 0) {
            while (cardsWithTag.size() != 0) {
                Flashcard flashcardToEdit = cardsWithTag.get(0);
                Flashcard editedFlashcard = createEditedTagFlashcard(flashcardToEdit, new Tag(newDeckName));
                model.setFlashcard(flashcardToEdit, editedFlashcard);
            }
        }
    }

    private Flashcard createEditedTagFlashcard(Flashcard flashcardToEdit, Tag editedTag) {
        assert flashcardToEdit != null;

        Question question = flashcardToEdit.getQuestion();
        Answer answer = flashcardToEdit.getAnswer();

        return new Flashcard(question, answer, editedTag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && index.equals(((EditDeckNameCommand) other).index))
                && newDeck.equals(((EditDeckNameCommand) other).newDeck); // state check
    }
}
