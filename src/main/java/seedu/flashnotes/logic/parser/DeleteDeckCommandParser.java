package seedu.flashnotes.logic.parser;


import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashnotes.logic.commands.DeleteDeckCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.deck.Deck;

public class DeleteDeckCommandParser implements Parser<DeleteDeckCommand> {


    @Override
    public DeleteDeckCommand parse(String args) throws ParseException {
        try {
            Deck deck = ParserUtil.parseDeckName(args);
            return new DeleteDeckCommand(deck);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeckCommand.MESSAGE_USAGE), pe);
        }
    }
}
