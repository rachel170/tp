package seedu.flashnotes.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDDECK_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CLEAR_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CORRECT_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETEDECK_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENDREVIEW_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENTERDECK_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FIND_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FLIP_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_FLIP_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_LIST_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_WRONG_COMMAND_IN_DECK_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.INVALID_WRONG_COMMAND_IN_HOME_MESSAGE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_ALREADY_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_EXTENDED_COMMAND_ERROR;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_IN_HOME;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNAVAILABLE_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.FlashcardBuilder.DEFAULT_TAG;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.AddCardCommand;
import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.CorrectCommand;
import seedu.flashnotes.logic.commands.DeleteCardCommand;
import seedu.flashnotes.logic.commands.DeleteDeckCommand;
import seedu.flashnotes.logic.commands.EditCardCommand;
import seedu.flashnotes.logic.commands.EndReviewCommand;
import seedu.flashnotes.logic.commands.EnterDeckCommand;
import seedu.flashnotes.logic.commands.ExitCommand;
import seedu.flashnotes.logic.commands.FindCommand;
import seedu.flashnotes.logic.commands.FlipCommand;
import seedu.flashnotes.logic.commands.HelpCommand;
import seedu.flashnotes.logic.commands.HomeCommand;
import seedu.flashnotes.logic.commands.ListCommand;
import seedu.flashnotes.logic.commands.ReviewCommand;
import seedu.flashnotes.logic.commands.SetReviewLimitCommand;
import seedu.flashnotes.logic.commands.WrongCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;
import seedu.flashnotes.testutil.FlashcardBuilder;
import seedu.flashnotes.testutil.FlashcardUtil;

/**
 * All testcases in this test class are testing the parseCommand method
 */
public class FlashNotesParserTest {
    private static final String DEFAULT = "Default";

    private final FlashNotesParser parser = new FlashNotesParser();
    private boolean isInDeck = true;
    private boolean isNotInDeck = false;
    private boolean isReviewMode = true;
    private boolean isNotReviewMode = false;

    //========================= Home mode testcases ===================================================================
    @Test
    public void homeScreen_addDeck_success() throws Exception {
        String keyword = "foo";
        Deck deck = new Deck(keyword);
        AddDeckCommand command = (AddDeckCommand) parser
                .parseCommand(AddDeckCommand.COMMAND_WORD + " n/" + keyword, isNotReviewMode,
                        isNotInDeck, DEFAULT);
        assertEquals(new AddDeckCommand(deck), command);
    }

    @Test
    public void homeScreen_deleteDeck_success() throws Exception {
        String keyword = "foo";
        Deck deck = new Deck(keyword);
        DeleteDeckCommand command = (DeleteDeckCommand) parser.parseCommand(
                DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isNotReviewMode, isNotInDeck, DEFAULT);
        assertEquals(new DeleteDeckCommand(deck), command);
    }

    @Test
    public void homeScreen_enterDeck_success() throws Exception {
        String keyword = "foo";
        EnterDeckCommand command = (EnterDeckCommand) parser.parseCommand(
                EnterDeckCommand.COMMAND_WORD + " " + keyword, isNotReviewMode, isNotInDeck, DEFAULT);
        assertEquals(new EnterDeckCommand(new TagContainsKeywordsPredicate(keyword)), command);
    }

    @Test
    public void homeScreen_exit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT) instanceof ExitCommand);
    }

    @Test
    public void homeScreen_help_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof HelpCommand);
    }

    @Test
    public void homeScreen_list_success() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof ListCommand);
    }

    @Test
    public void homeScreen_clear_success() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof ClearCommand);
    }

    //    @Test
    //    public void parseCommand_homeScreen_find_success() throws Exception {
    //      Not implemented yet
    //    }

    @Test
    public void homeScreen_addCard_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(AddCardCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_editCard_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(EditCardCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_deleteCard_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(DeleteCardCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_review_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(ReviewCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_flip_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_FLIP_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(FlipCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_correct_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(CorrectCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_wrong_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_WRONG_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(WrongCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_endReview_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE, ()
            -> parser.parseCommand(EndReviewCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_home_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(HomeCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    //    @Test
    //    public void parseCommand_homeScreen_setReviewLimit_throwsParseException() throws Exception {
    //      Not implemented yet
    //    }

    @Test
    public void homeScreen_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", isNotReviewMode, isNotInDeck, DEFAULT));
    }

    //========================= Card mode testcases ===================================================================
    @Test
    public void inDeck_add_success() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        AddCardCommand command = (AddCardCommand) parser
                .parseCommand(FlashcardUtil.getAddCardCommand(flashcard), isNotReviewMode, isInDeck, DEFAULT_TAG);
        assertEquals(new AddCardCommand(flashcard), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        // Clear command created by default
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, isNotReviewMode, !isInDeck, DEFAULT)
                instanceof ClearCommand);
        // Throws ParseException if Clear command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ClearCommand.COMMAND_WORD), ()
            -> parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", isNotReviewMode, !isInDeck, DEFAULT));

    }

    @Test
    public void inDeck_delete_success() throws Exception {
        DeleteCardCommand command = (DeleteCardCommand) parser.parseCommand(
                DeleteCardCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased(),
                isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new DeleteCardCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void inDeck_edit_success() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(flashcard).build();
        EditCardCommand command = (EditCardCommand) parser.parseCommand(EditCardCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor), isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new EditCardCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        // Able to create ExitCommand by default
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, isNotReviewMode, isInDeck, DEFAULT)
                instanceof ExitCommand);
        // Throws ParseException if Exit command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ExitCommand.COMMAND_WORD), ()
            -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_exit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT) instanceof ExitCommand);

    }

    @Test
    public void inDeck_find_success() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")),
                isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new FindCommand(new QuestionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void inDeck_help_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isNotReviewMode, isInDeck, DEFAULT)
                instanceof HelpCommand);
    }

    @Test
    public void inDeck_home_success() throws Exception {
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD, isNotReviewMode, isInDeck, DEFAULT)
                instanceof HomeCommand);
    }

    @Test
    public void inDeck_review_success() throws Exception {
        assertTrue(parser.parseCommand(ReviewCommand.COMMAND_WORD, isNotReviewMode, isInDeck, DEFAULT)
                instanceof ReviewCommand);
    }

    @Test
    public void inDeck_setReviewLimit_success() throws Exception {
        assertTrue(parser.parseCommand(SetReviewLimitCommand.COMMAND_WORD + " 20", isNotReviewMode, isInDeck, DEFAULT)
                instanceof SetReviewLimitCommand);
    }

    @Test
    public void inDeck_addDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, INVALID_ADDDECK_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isNotReviewMode,
                        isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_deleteDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, INVALID_DELETEDECK_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_enterDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, INVALID_ENTERDECK_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(EnterDeckCommand.COMMAND_WORD + " " + keyword,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_help() throws Exception {
        // Able to create HelpCommand by default
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isNotReviewMode, isInDeck, DEFAULT)
                instanceof HelpCommand);
        // Throws ParseException if Help command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, HelpCommand.COMMAND_WORD), ()
            -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_list() throws Exception {
        // Able to create ListCommand by default
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, isNotReviewMode, !isInDeck, DEFAULT)
                instanceof ListCommand);
        // Throws ParseException if List command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ListCommand.COMMAND_WORD), ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3", isNotReviewMode, !isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_clear_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_CLEAR_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(ClearCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_list_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_LIST_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_flip_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_FLIP_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(FlipCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_correct_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_CORRECT_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(CorrectCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_wrong_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_WRONG_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(WrongCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_endReview_throwsParseException() throws Exception {
        assertThrows(ParseException.class, INVALID_ENDREVIEW_COMMAND_IN_DECK_MESSAGE, ()
            -> parser.parseCommand(EndReviewCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }


    @Test
    public void inDeck_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", isNotReviewMode, isInDeck, DEFAULT));
    }

    //========================= Review mode testcases =================================================================
    @Test
    public void inReview_flip_success() throws Exception {
        assertTrue(parser.parseCommand(FlipCommand.COMMAND_WORD, isReviewMode, isNotInDeck, DEFAULT)
                instanceof FlipCommand);
    }

    @Test
    public void inReview_correct_success() throws Exception {
        assertTrue(parser.parseCommand(CorrectCommand.COMMAND_WORD, isReviewMode, isNotInDeck, DEFAULT)
                instanceof CorrectCommand);
    }

    @Test
    public void inReview_wrong_success() throws Exception {
        assertTrue(parser.parseCommand(WrongCommand.COMMAND_WORD, isReviewMode, isNotInDeck, DEFAULT)
                instanceof WrongCommand);
    }

    @Test
    public void inReview_endReview_success() throws Exception {
        assertTrue(parser.parseCommand(EndReviewCommand.COMMAND_WORD, isReviewMode, isNotInDeck, DEFAULT)
                instanceof EndReviewCommand);
    }

    @Test
    public void inReview_help_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isReviewMode, isNotInDeck, DEFAULT)
                instanceof HelpCommand);
    }

    @Test
    public void inReview_review_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_ALREADY_IN_REVIEW_MODE, ()
            -> parser.parseCommand(ReviewCommand.COMMAND_WORD, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_addDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_deleteDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_enterDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(EnterDeckCommand.COMMAND_WORD + " " + keyword, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_list_throwsParseException() {
        assertThrows(ParseException.class, INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_clear_throwsParseException() {
        assertThrows(ParseException.class, INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(ClearCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_find_throwsParseException() {
        assertThrows(ParseException.class, INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(FindCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_setReviewLimit_throwsParseException() {
        assertThrows(ParseException.class, INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(SetReviewLimitCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_exit_throwsParseException() {
        assertThrows(ParseException.class, INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(ExitCommand.COMMAND_WORD, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_addCard_throwsParseException() {
        assertThrows(ParseException.class, INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(AddCardCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_editCard_throwsParseException() {
        assertThrows(ParseException.class, INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(EditCardCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_deleteCard_throwsParseException() {
        assertThrows(ParseException.class, INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE, ()
            -> parser.parseCommand(DeleteCardCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_home_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(HomeCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", isReviewMode, isNotInDeck, DEFAULT));

    }

    @Test
    public void inReview_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", isReviewMode, isNotInDeck, DEFAULT));
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
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
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
