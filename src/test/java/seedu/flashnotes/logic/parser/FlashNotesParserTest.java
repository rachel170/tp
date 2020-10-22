package seedu.flashnotes.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FIND_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FLIP_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_HOME_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_WRONG_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.FlashcardBuilder.DEFAULT_TAG;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.AddCardCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.DeleteCardCommand;
import seedu.flashnotes.logic.commands.EditCardCommand;
import seedu.flashnotes.logic.commands.EnterDeckCommand;
import seedu.flashnotes.logic.commands.ExitCommand;
import seedu.flashnotes.logic.commands.FindCommand;
import seedu.flashnotes.logic.commands.HelpCommand;
import seedu.flashnotes.logic.commands.ListCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;
import seedu.flashnotes.testutil.FlashcardBuilder;
import seedu.flashnotes.testutil.FlashcardUtil;

public class FlashNotesParserTest {
    private static final String DEFAULT = "Default";

    private final FlashNotesParser parser = new FlashNotesParser();
    private boolean isInDeck = true;
    private boolean isReviewMode = false;

    @Test
    public void parseCommand_add() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        AddCardCommand command = (AddCardCommand) parser
                .parseCommand(FlashcardUtil.getAddCardCommand(flashcard), isReviewMode, isInDeck, DEFAULT_TAG);
        assertEquals(new AddCardCommand(flashcard), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, isReviewMode, !isInDeck, DEFAULT)
                instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", isReviewMode, !isInDeck, DEFAULT)
                instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCardCommand command = (DeleteCardCommand) parser.parseCommand(
                DeleteCardCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased(),
                isReviewMode, isInDeck, DEFAULT);
        assertEquals(new DeleteCardCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(flashcard).build();
        EditCardCommand command = (EditCardCommand) parser.parseCommand(EditCardCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor), isReviewMode, isInDeck, DEFAULT);
        assertEquals(new EditCardCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isReviewMode, isInDeck, DEFAULT) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3",
                isReviewMode, isInDeck, DEFAULT) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")),
                isReviewMode, isInDeck, DEFAULT);
        assertEquals(new FindCommand(new QuestionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_enterTag() throws Exception {
        String keyword = "foo";
        EnterDeckCommand command = (EnterDeckCommand) parser.parseCommand(
                EnterDeckCommand.COMMAND_WORD + " " + keyword, isReviewMode, !isInDeck, DEFAULT);
        assertEquals(new EnterDeckCommand(new TagContainsKeywordsPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isReviewMode, isInDeck, DEFAULT)
                instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3",
                isReviewMode, isInDeck, DEFAULT) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, isReviewMode, !isInDeck, DEFAULT)
                instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3",
                isReviewMode, !isInDeck, DEFAULT) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", isReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", isReviewMode, isInDeck, DEFAULT));
    }

    // Parse Invalid Commands in Review Mode
    @Test
    public void parseCommand_addCardInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("addCard q/What? a/nothing", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_editCardInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("editCard 1", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_deleteCardInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("deleteCard 1", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_clearInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("clear", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_findInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("find econs", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_setReviewLimitInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("setReviewLimit 10", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_enterDeckInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("enterDeck default", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_listInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("list", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_addDeckInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("addDeck History", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_exitInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("exit", true, false, DEFAULT));
    }

    @Test
    public void parseCommand_deleteDeckInReviewMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE, ()
                -> parser.parseCommand("deleteDeck Economics", true, false, DEFAULT));
    }

    // Invalid Commands in Home Screen
    @Test
    public void parseCommand_addCardInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("addCard q/What? a/nothing", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_editCardInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("editCard 1", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_deleteCardInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("deleteCard 1", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_findInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_FIND_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("find economics", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_reviewInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("review", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_correctInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("c", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_flipInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_FLIP_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("flip", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_homeInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_HOME_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("home", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_wrongInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_WRONG_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("w", false, false, DEFAULT));
    }

    @Test
    public void parseCommand_endReviewInHomeMode_throwsParseException() {
        assertThrows(ParseException.class, INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE, ()
                -> parser.parseCommand("endReview", false, false, DEFAULT));
    }

}
