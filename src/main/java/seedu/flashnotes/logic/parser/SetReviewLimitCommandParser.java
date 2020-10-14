package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashnotes.logic.commands.SetReviewLimitCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new SetReviewLimitCommand object
 */
public class SetReviewLimitCommandParser implements Parser<SetReviewLimitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetReviewLimitCommand
     * and returns a SetReviewLimitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetReviewLimitCommand parse(String args) throws ParseException {
        try {
            Integer reviewCardLimit = ParserUtil.parseInteger(args);
            return new SetReviewLimitCommand(reviewCardLimit);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetReviewLimitCommand.MESSAGE_USAGE), pe);
        }
    }

}
