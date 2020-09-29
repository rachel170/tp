package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.flashnotes.logic.commands.CommandTestUtil.QUESTION_DESC_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.QUESTION_DESC_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.AMY;
import static seedu.flashnotes.testutil.TypicalFlashcards.BOB;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.AddCommand;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;
import seedu.flashnotes.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedFlashcard = new FlashcardBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_BOB + ANSWER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));

        // multiple questions - last question accepted
        assertParseSuccess(parser, QUESTION_DESC_AMY + QUESTION_DESC_BOB + ANSWER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));

        // multiple answers - last answer accepted
        assertParseSuccess(parser, QUESTION_DESC_BOB + ANSWER_DESC_AMY + ANSWER_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedFlashcard));


        // multiple tags - all accepted
        Flashcard expectedFlashcardMultipleTags = new FlashcardBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, QUESTION_DESC_BOB + ANSWER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedFlashcardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Flashcard expectedFlashcard = new FlashcardBuilder(AMY).withTags().build();
        assertParseSuccess(parser, QUESTION_DESC_AMY + ANSWER_DESC_AMY,
                new AddCommand(expectedFlashcard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing question prefix
        assertParseFailure(parser, VALID_QUESTION_BOB + ANSWER_DESC_BOB,
                expectedMessage);

        // missing answer prefix
        assertParseFailure(parser, QUESTION_DESC_BOB + VALID_ANSWER_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_BOB + VALID_ANSWER_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + ANSWER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Question.MESSAGE_CONSTRAINTS);

        // invalid answer
        assertParseFailure(parser, QUESTION_DESC_BOB + INVALID_ANSWER_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Answer.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, QUESTION_DESC_BOB + ANSWER_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_BOB + ANSWER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
