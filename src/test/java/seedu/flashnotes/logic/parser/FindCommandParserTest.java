package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.FindCommand;
import seedu.flashnotes.model.flashcard.QuestionContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new QuestionContainsKeywordsPredicate(Arrays.asList("Why", "How")));
        assertParseSuccess(parser, "Why How", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Why \n \t How  \t", expectedFindCommand);
    }

}
