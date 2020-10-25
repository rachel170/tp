package seedu.flashnotes.logic.parser;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DECK_DESC_ECONOMICS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DECK_DESC_NATURE;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.model.deck.Deck;


public class AddDeckCommandParserTest {
    private AddDeckCommandParser parser = new AddDeckCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Deck expectedDeck = new Deck("Economics");

        // whitespace only preamble
        assertParseSuccess(parser, DECK_DESC_ECONOMICS,
                new AddDeckCommand(expectedDeck));

        //multiple deck names - last name accepted
        assertParseSuccess(parser, DECK_DESC_NATURE + DECK_DESC_ECONOMICS,
                new AddDeckCommand(expectedDeck));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeckCommand.MESSAGE_USAGE);

        // missing argument for deck name
        assertParseFailure(parser, "n/", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "deckName", expectedMessage);
    }

}
