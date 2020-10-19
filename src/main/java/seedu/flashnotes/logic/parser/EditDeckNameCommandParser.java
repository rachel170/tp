package seedu.flashnotes.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_NEW_DECK_NAME;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.EditDeckNameCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;

public class EditDeckNameCommandParser implements Parser<EditDeckNameCommand> {

    @Override
    public EditDeckNameCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_NEW_DECK_NAME);

        Index index;

        if (!argMultiMap.getValue(PREFIX_NEW_DECK_NAME).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDeckNameCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDeckNameCommand.MESSAGE_USAGE), pe);
        }

        String newDeckName = argMultiMap.getValue(PREFIX_NEW_DECK_NAME).get().trim();


        return new EditDeckNameCommand(index, newDeckName);

    }


}
