package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.EnterTagCommand;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

public class EnterTagCommandParserTest {

    private EnterTagCommandParser parser = new EnterTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EnterTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListTagsCommand() {
        // no leading and trailing whitespaces
        EnterTagCommand expectedListTagCommand =
                new EnterTagCommand(new TagContainsKeywordsPredicate("friends"));
        assertParseSuccess(parser, "friends", expectedListTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n friends \n", expectedListTagCommand);
    }
}
