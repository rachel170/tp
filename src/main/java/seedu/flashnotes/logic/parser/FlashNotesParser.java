package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_ALREADY_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_EXTENDED_COMMAND_ERROR;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_IN_CARD;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_IN_HOME;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNAVAILABLE_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.flashnotes.logic.commands.AddCommand;
import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.logic.commands.CheckReviewLimitCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.Command;
import seedu.flashnotes.logic.commands.CorrectCommand;
import seedu.flashnotes.logic.commands.DeleteCommand;
import seedu.flashnotes.logic.commands.DeleteDeckCommand;
import seedu.flashnotes.logic.commands.EditCommand;
import seedu.flashnotes.logic.commands.EditDeckNameCommand;
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
            //assert isInDeck : "Program should be in card mode before entering review mode";
            return parseCommandInReviewMode(commandWord, arguments);
        }

        if (!isInDeck) {
            //assert !isReviewMode : "Program should not be in review mode";
            return parseCommandInHomeMode(commandWord, arguments);
        } else {
            //assert !isReviewMode : "Program should not be in review mode";
            return parseCommandInCardMode(commandWord, arguments, deckName);
        }
    }


    private Command parseCommandInReviewMode(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddDeckCommand.COMMAND_WORD:
        case DeleteDeckCommand.COMMAND_WORD:
        case EnterDeckCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case SetReviewLimitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case CheckReviewLimitCommand.COMMAND_WORD:
        case AddCommand.COMMAND_WORD:
        case EditDeckNameCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case HomeCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_UNAVAILABLE_IN_REVIEW_MODE);

        case ReviewCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_ALREADY_IN_REVIEW_MODE);

        case FlipCommand.COMMAND_WORD:
            // There should be no arguments for flip command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, FlipCommand.COMMAND_WORD));
            }
            return new FlipCommand();

        case CorrectCommand.COMMAND_WORD:
            // There should be no arguments for correct command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, CorrectCommand.COMMAND_WORD));
            }
            return new CorrectCommand();

        case WrongCommand.COMMAND_WORD:
            // There should be no arguments for wrong command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, WrongCommand.COMMAND_WORD));
            }
            return new WrongCommand();

        case EndReviewCommand.COMMAND_WORD:
            // There should be no arguments for endReview command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, EndReviewCommand.COMMAND_WORD));
            }

            return new EndReviewCommand();

        case HelpCommand.COMMAND_WORD:
            // There should be no arguments for help command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, HelpCommand.COMMAND_WORD));
            }
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseCommandInHomeMode(
            String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case ReviewCommand.COMMAND_WORD:
        case FlipCommand.COMMAND_WORD:
        case CorrectCommand.COMMAND_WORD:
        case WrongCommand.COMMAND_WORD:
        case EndReviewCommand.COMMAND_WORD:
        case HomeCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_INVALID_COMMAND_IN_HOME);

        case AddDeckCommand.COMMAND_WORD:
            return new AddDeckCommandParser().parse(arguments);

        case DeleteDeckCommand.COMMAND_WORD:
            return new DeleteDeckCommandParser().parse(arguments);

        case EnterDeckCommand.COMMAND_WORD:
            return new EnterDeckCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            // There should be no arguments for exit command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ExitCommand.COMMAND_WORD));
            }
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            // There should be no arguments for help command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, HelpCommand.COMMAND_WORD));
            }
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            // There should be no arguments for list command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ListCommand.COMMAND_WORD));
            }
            return new ListCommand();

        case ClearCommand.COMMAND_WORD:
            // There should be no arguments for clear command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ClearCommand.COMMAND_WORD));
            }
            return new ClearCommand();

        case EditDeckNameCommand.COMMAND_WORD:
            return new EditDeckNameCommandParser().parse(arguments);
        case SetReviewLimitCommand.COMMAND_WORD:
            return new SetReviewLimitCommandParser().parse(arguments);

        case CheckReviewLimitCommand.COMMAND_WORD:
            return new CheckReviewLimitCommand();

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
        case EditDeckNameCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_INVALID_COMMAND_IN_CARD);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments, deckName);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            // There should be no arguments for exit command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ExitCommand.COMMAND_WORD));
            }
            return new ExitCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            // There should be no arguments for help command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, HelpCommand.COMMAND_WORD));
            }
            return new HelpCommand();

        case HomeCommand.COMMAND_WORD:
            return new HomeCommandParser().parse(arguments);

        case ReviewCommand.COMMAND_WORD:
            // There should be no arguments for review command
            if (hasArguments(arguments)) {
                // If arguments exist, throw ParseException
                throw new ParseException(String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ReviewCommand.COMMAND_WORD));
            }
            return new ReviewCommand();

        case SetReviewLimitCommand.COMMAND_WORD:
            return new SetReviewLimitCommandParser().parse(arguments);
        case CheckReviewLimitCommand.COMMAND_WORD:
            return new CheckReviewLimitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Checks argument String for values.
     *
     * @param arguments arguments string from user input
     * @return true if string is contains anything beside whitespace, false otherwise
     */
    private boolean hasArguments(String arguments) {
        if (!arguments.isBlank()) {
            return true;
        } else {
            return false;
        }
    }

}
