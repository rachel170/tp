package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_ALREADY_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNAVAILABLE_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.flashnotes.logic.commands.AddCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.Command;
import seedu.flashnotes.logic.commands.CorrectCommand;
import seedu.flashnotes.logic.commands.DeleteCommand;
import seedu.flashnotes.logic.commands.EditCommand;
import seedu.flashnotes.logic.commands.ExitCommand;
import seedu.flashnotes.logic.commands.FindCommand;
import seedu.flashnotes.logic.commands.FlipCommand;
import seedu.flashnotes.logic.commands.HelpCommand;
import seedu.flashnotes.logic.commands.ListCommand;
import seedu.flashnotes.logic.commands.ListTagsCommand;
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

    private boolean isReviewMode = false;

    private Command parseCommandInReviewMode(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case SetReviewLimitCommand.COMMAND_WORD:
        case ListTagsCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_UNAVAILABLE_IN_REVIEW_MODE);

        case ReviewCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_ALREADY_IN_REVIEW_MODE);

        case FlipCommand.COMMAND_WORD:
            return new FlipCommand();

        case CorrectCommand.COMMAND_WORD:
            return new CorrectCommand();

        case WrongCommand.COMMAND_WORD:
            return new WrongCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseCommandInNormalMode(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ReviewCommand.COMMAND_WORD:
            this.isReviewMode = true;
            return new ReviewCommand();

        case SetReviewLimitCommand.COMMAND_WORD:
            return new SetReviewLimitCommandParser().parse(arguments);

        case ListTagsCommand.COMMAND_WORD:
            return new ListTagsCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (this.isReviewMode) {
            return parseCommandInReviewMode(commandWord, arguments);
        } else {
            return parseCommandInNormalMode(commandWord, arguments);
        }
    }

}
