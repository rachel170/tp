package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDDECK_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CLEAR_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CORRECT_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETEDECK_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENDREVIEW_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENTERDECK_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FIND_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FLIP_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FLIP_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_HOME_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_LIST_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_WRONG_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_WRONG_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_ALREADY_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNAVAILABLE_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.flashnotes.logic.commands.AddCardCommand;
import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.Command;
import seedu.flashnotes.logic.commands.CorrectCommand;
import seedu.flashnotes.logic.commands.DeleteCardCommand;
import seedu.flashnotes.logic.commands.DeleteDeckCommand;
import seedu.flashnotes.logic.commands.EditCardCommand;
import seedu.flashnotes.logic.commands.EndReviewCommand;
import seedu.flashnotes.logic.commands.EnterDeckCommand;
import seedu.flashnotes.logic.commands.ExitCommand;
import seedu.flashnotes.logic.commands.FindCommand;
import seedu.flashnotes.logic.commands.FlipCommand;
import seedu.flashnotes.logic.commands.HelpCommand;
import seedu.flashnotes.logic.commands.HomeCommand;
import seedu.flashnotes.logic.commands.ListCommand;
import seedu.flashnotes.logic.commands.ReviewCommand;
import seedu.flashnotes.logic.commands.SetReviewLimitCommand;
import seedu.flashnotes.logic.commands.WrongCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FlashNotesParser {
    private static final String ILLEGAL_COMMAND_IN_HOME_MESSAGE = "Cannot call command in home screen.";
    private static final String ILLEGAL_COMMAND_IN_CARD_MESSAGE = "Cannot call command in card screen.";

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, boolean isReviewMode, boolean isInDeck, String deckName)
            throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (isReviewMode) {
            return parseCommandInReviewMode(commandWord, arguments);
        }

        if (!isInDeck) {
            return parseCommandInHomeMode(commandWord, arguments);
        } else {
            return parseCommandInCardMode(commandWord, arguments, deckName.toLowerCase());
        }
    }


    private Command parseCommandInReviewMode(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddCardCommand.COMMAND_WORD:
        case EditCardCommand.COMMAND_WORD:
        case DeleteCardCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case SetReviewLimitCommand.COMMAND_WORD:
        case EnterDeckCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
        case AddDeckCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case DeleteDeckCommand.COMMAND_WORD:
            return parseInvalidCommandInReviewMode(commandWord);

        case ReviewCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_ALREADY_IN_REVIEW_MODE);

        case FlipCommand.COMMAND_WORD:
            return new FlipCommand();

        case CorrectCommand.COMMAND_WORD:
            return new CorrectCommand();

        case WrongCommand.COMMAND_WORD:
            return new WrongCommand();

        case EndReviewCommand.COMMAND_WORD:
            return new EndReviewCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseInvalidCommandInReviewMode(
            String commandWord) throws ParseException {

        switch (commandWord) {

        case AddCardCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE);

        case EditCardCommand.COMMAND_WORD:
            throw new ParseException(INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE);

        case DeleteCardCommand.COMMAND_WORD:
            throw new ParseException(INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE);

        case ClearCommand.COMMAND_WORD:
            throw new ParseException(INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE);

        case FindCommand.COMMAND_WORD:
            throw new ParseException(INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE);

        case SetReviewLimitCommand.COMMAND_WORD:
            throw new ParseException(INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE);

        case EnterDeckCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE);

        case ListCommand.COMMAND_WORD:
            throw new ParseException(INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE);

        case AddDeckCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE);

        case ExitCommand.COMMAND_WORD:
            throw new ParseException(INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE);

        case DeleteDeckCommand.COMMAND_WORD:
            throw new ParseException(INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE);

        default:
            throw new ParseException(MESSAGE_UNAVAILABLE_IN_REVIEW_MODE);
        }
    }

    private Command parseCommandInHomeMode(
            String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCardCommand.COMMAND_WORD:
        case EditCardCommand.COMMAND_WORD:
        case DeleteCardCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case ReviewCommand.COMMAND_WORD:
        case CorrectCommand.COMMAND_WORD:
        case FlipCommand.COMMAND_WORD:
        case HomeCommand.COMMAND_WORD:
        case WrongCommand.COMMAND_WORD:
        case EndReviewCommand.COMMAND_WORD:
            return parseInvalidCommandInHomeMode(commandWord);

        case AddDeckCommand.COMMAND_WORD:
            return new AddDeckCommandParser().parse(arguments.toLowerCase());

        case DeleteDeckCommand.COMMAND_WORD:
            return new DeleteDeckCommandParser().parse(arguments.toLowerCase());

        case EnterDeckCommand.COMMAND_WORD:
            return new EnterDeckCommandParser().parse(arguments.toLowerCase());

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseInvalidCommandInHomeMode(
            String commandWord) throws ParseException {

        switch (commandWord) {

        case AddCardCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE);

        case EditCardCommand.COMMAND_WORD:
            throw new ParseException(INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE);

        case DeleteCardCommand.COMMAND_WORD:
            throw new ParseException(INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE);

        case FindCommand.COMMAND_WORD:
            throw new ParseException(INVALID_FIND_COMMAND_IN_HOME_MESSAGE);

        case ReviewCommand.COMMAND_WORD:
            throw new ParseException(INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE);

        case CorrectCommand.COMMAND_WORD:
            throw new ParseException(INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE);

        case FlipCommand.COMMAND_WORD:
            throw new ParseException(INVALID_FLIP_COMMAND_IN_HOME_MESSAGE);

        case HomeCommand.COMMAND_WORD:
            throw new ParseException(INVALID_HOME_COMMAND_IN_HOME_MESSAGE);

        case WrongCommand.COMMAND_WORD:
            throw new ParseException(INVALID_WRONG_COMMAND_IN_HOME_MESSAGE);

        case EndReviewCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseCommandInCardMode(
            String commandWord, String arguments, String deckName) throws ParseException {
        switch (commandWord) {
        case AddDeckCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case CorrectCommand.COMMAND_WORD:
        case DeleteDeckCommand.COMMAND_WORD:
        case EnterDeckCommand.COMMAND_WORD:
        case FlipCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
        case EndReviewCommand.COMMAND_WORD:
        case WrongCommand.COMMAND_WORD:
            return parseInvalidCommandInDeckMode(commandWord);

        case AddCardCommand.COMMAND_WORD:
            return new AddCardCommandParser().parse(arguments, deckName);
        case DeleteCardCommand.COMMAND_WORD:
            return new DeleteCardCommandParser().parse(arguments);
        case EditCardCommand.COMMAND_WORD:
            return new EditCardCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case HomeCommand.COMMAND_WORD:
            return new HomeCommandParser().parse(arguments);
        case ReviewCommand.COMMAND_WORD:
            return new ReviewCommand();
        case SetReviewLimitCommand.COMMAND_WORD:
            return new SetReviewLimitCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }
    }

    private Command parseInvalidCommandInDeckMode(
            String commandWord) throws ParseException {

        switch (commandWord) {
        case AddDeckCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ADDDECK_COMMAND_IN_DECK_MESSAGE);
        case ClearCommand.COMMAND_WORD:
            throw new ParseException(INVALID_CLEAR_COMMAND_IN_DECK_MESSAGE);
        case CorrectCommand.COMMAND_WORD:
            throw new ParseException(INVALID_CORRECT_COMMAND_IN_DECK_MESSAGE);
        case DeleteDeckCommand.COMMAND_WORD:
            throw new ParseException(INVALID_DELETEDECK_COMMAND_IN_DECK_MESSAGE);
        case EnterDeckCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ENTERDECK_COMMAND_IN_DECK_MESSAGE);
        case FlipCommand.COMMAND_WORD:
            throw new ParseException(INVALID_FLIP_COMMAND_IN_DECK_MESSAGE);
        case ListCommand.COMMAND_WORD:
            throw new ParseException(INVALID_LIST_COMMAND_IN_DECK_MESSAGE);
        case EndReviewCommand.COMMAND_WORD:
            throw new ParseException(INVALID_ENDREVIEW_COMMAND_IN_DECK_MESSAGE);
        case WrongCommand.COMMAND_WORD:
            throw new ParseException(INVALID_WRONG_COMMAND_IN_DECK_MESSAGE);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }
    }

}
