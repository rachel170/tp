package seedu.flashnotes.logic.parser;

import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.deck.Deck;

import java.util.stream.Stream;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_DECK_NAME;

public class AddDeckCommandParser implements Parser<AddDeckCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddDeckCommand
     * and returns an AddDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeckCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DECK_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DECK_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeckCommand.MESSAGE_USAGE));
        }

        Deck theDeck = ParserUtil.parseDeckName(argMultimap.getValue(PREFIX_DECK_NAME).get());

        return new AddDeckCommand(theDeck);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
