package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.flashnotes.logic.commands.CommandTestUtil.QUESTION_DESC_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.QUESTION_DESC_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_MACROECONS;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.MACROECONS;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.AddCommand;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedFlashcard = new FlashcardBuilder(MACROECONS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_MACROECONS + ANSWER_DESC_MACROECONS,
                new AddCommand(expectedFlashcard));

        String test;
        // multiple questions - last question accepted
        assertParseSuccess(parser, QUESTION_DESC_SKY + QUESTION_DESC_MACROECONS + ANSWER_DESC_MACROECONS,
                new AddCommand(expectedFlashcard));

        // multiple answers - last answer accepted
        assertParseSuccess(parser, QUESTION_DESC_MACROECONS + ANSWER_DESC_SKY + ANSWER_DESC_MACROECONS,
                new AddCommand(expectedFlashcard));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing question prefix
        assertParseFailure(parser, VALID_QUESTION_MACROECONS + ANSWER_DESC_MACROECONS,
                expectedMessage);

        // missing answer prefix
        assertParseFailure(parser, QUESTION_DESC_MACROECONS + VALID_ANSWER_MACROECONS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_MACROECONS + VALID_ANSWER_MACROECONS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + ANSWER_DESC_MACROECONS,
                Question.MESSAGE_CONSTRAINTS);

        // invalid answer
        assertParseFailure(parser, QUESTION_DESC_MACROECONS + INVALID_ANSWER_DESC,
                Answer.MESSAGE_CONSTRAINTS);


        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_MACROECONS + ANSWER_DESC_MACROECONS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
