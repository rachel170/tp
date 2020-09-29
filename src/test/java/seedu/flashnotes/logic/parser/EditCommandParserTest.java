package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.QUESTION_DESC_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_THIRD_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.EditCommand;
import seedu.flashnotes.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC, Question.MESSAGE_CONSTRAINTS); // invalid question
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS); // invalid answer
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // valid answer followed by invalid answer. The test case for invalid answer followed by valid answer
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ANSWER_DESC_BOB + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Flashcard} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC + VALID_ANSWER_AMY,
                Question.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_BOB + TAG_DESC_HUSBAND
                + QUESTION_DESC_AMY + TAG_DESC_FRIEND;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_AMY)
                .withAnswer(VALID_ANSWER_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_BOB;

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // question
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_AMY;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // answer
        userInput = targetIndex.getOneBased() + ANSWER_DESC_AMY;
        descriptor = new EditFlashcardDescriptorBuilder().withAnswer(VALID_ANSWER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditFlashcardDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_AMY
                + TAG_DESC_FRIEND + ANSWER_DESC_AMY + TAG_DESC_FRIEND
                + ANSWER_DESC_BOB + TAG_DESC_HUSBAND;

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + INVALID_ANSWER_DESC + ANSWER_DESC_BOB;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_ANSWER_DESC
                + ANSWER_DESC_BOB;
        descriptor = new EditFlashcardDescriptorBuilder().withAnswer(VALID_ANSWER_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
