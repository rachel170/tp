package seedu.flashnotes.logic.parser;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashnotes.logic.commands.HomeCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;


public class HomeCommandParser implements Parser<HomeCommand> {
    @Override
    public HomeCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (!trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HomeCommand.MESSAGE_USAGE));
        }

        return new HomeCommand();
    }
}
