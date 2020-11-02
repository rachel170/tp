package seedu.flashnotes.logic.parser;


import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.DeleteDeckCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;

public class DeleteDeckCommandParser implements Parser<DeleteDeckCommand> {


    @Override
    public DeleteDeckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDeckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT
                            + "\n" + pe.getMessage(), DeleteDeckCommand.MESSAGE_USAGE), pe);
        }
    }
}
