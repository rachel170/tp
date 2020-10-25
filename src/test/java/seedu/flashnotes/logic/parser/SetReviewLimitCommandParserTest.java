package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.SetReviewLimitCommand;

/**
 * Test class for SetReviewLimitCommandParser.
 */
public class SetReviewLimitCommandParserTest {

    private SetReviewLimitCommandParser parser = new SetReviewLimitCommandParser();

    @Test
    public void parse_validArgs_returnsSetReviewLimitCommand() {
        assertParseSuccess(parser, "20", new SetReviewLimitCommand(20));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetReviewLimitCommand.MESSAGE_USAGE));
    }
}

