package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.ANSWER_DESC_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.flashnotes.logic.commands.CommandTestUtil.QUESTION_DESC_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.TAG_DESC_ECONOMICS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.TAG_DESC_NATURE;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_ECONOMICS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_NATURE;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_THIRD_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.EditCardCommand;
import seedu.flashnotes.logic.commands.EditCardCommand.EditFlashcardDescriptor;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;

public class EditCardCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE);

    private EditCardCommandParser parser = new EditCardCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_SKY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCardCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUESTION_DESC_SKY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUESTION_DESC_SKY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC,
                String.format(Question.MESSAGE_CONSTRAINTS, 0)); // invalid question
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC,
                String.format(Answer.MESSAGE_CONSTRAINTS, 0)); // invalid answer


        /// valid answer followed by invalid answer. The test case for invalid answer followed by valid answer
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ANSWER_DESC_MACROECONS + INVALID_ANSWER_DESC,
                String.format(Answer.MESSAGE_CONSTRAINTS, 0));

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Flashcard} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_ECONOMICS + TAG_DESC_NATURE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_MACROECONS + TAG_DESC_NATURE
                + QUESTION_DESC_SKY;

        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_SKY)
                .withAnswer(VALID_ANSWER_MACROECONS).withTag(VALID_TAG_NATURE).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_MACROECONS;

        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_MACROECONS).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // question
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_SKY;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_SKY).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // answer
        userInput = targetIndex.getOneBased() + ANSWER_DESC_SKY;
        descriptor = new EditFlashcardDescriptorBuilder().withAnswer(VALID_ANSWER_SKY).build();
        expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tag
        userInput = targetIndex.getOneBased() + TAG_DESC_ECONOMICS;
        descriptor = new EditFlashcardDescriptorBuilder().withTag(VALID_TAG_ECONOMICS).build();
        expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_SKY
                + TAG_DESC_ECONOMICS + ANSWER_DESC_SKY + TAG_DESC_ECONOMICS
                + ANSWER_DESC_MACROECONS + TAG_DESC_NATURE;

        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_MACROECONS)
                .withTag(VALID_TAG_NATURE).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + INVALID_ANSWER_DESC + ANSWER_DESC_MACROECONS;
        EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_MACROECONS).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_ANSWER_DESC
                + ANSWER_DESC_MACROECONS;
        descriptor = new EditFlashcardDescriptorBuilder().withAnswer(VALID_ANSWER_MACROECONS).build();
        expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
