package seedu.flashnotes.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.logic.commands.HomeCommand;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DECK_DESC_ECONOMICS;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class HomeCommandParserTest {
    private HomeCommandParser parser = new HomeCommandParser();

    //parse success
    @Test
    public void parse_allFieldsPresent_success() {
        // empty arguments
        assertParseSuccess(parser, "",
                new HomeCommand());

    }

    //parse exception due to extra arguments
    @Test
    public void parse_extraFieldsPresent_throwParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HomeCommand.MESSAGE_USAGE);
        // empty arguments
        assertParseFailure(parser, "funky arguments behind",
                expectedMessage);
    }

}
