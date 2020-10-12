package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.EnterDeckCommand;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

public class EnterDeckCommandParserTest {

    private EnterDeckCommandParser parser = new EnterDeckCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EnterDeckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListTagsCommand() {
        // no leading and trailing whitespaces
        EnterDeckCommand expectedListTagCommand =
                new EnterDeckCommand(new TagContainsKeywordsPredicate("friends"));
        assertParseSuccess(parser, "friends", expectedListTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n friends \n", expectedListTagCommand);
    }
}
