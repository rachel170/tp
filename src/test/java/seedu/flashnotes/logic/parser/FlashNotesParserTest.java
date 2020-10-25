package seedu.flashnotes.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_EXTENDED_COMMAND_ERROR;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_ALREADY_IN_REVIEW_MODE;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_IN_CARD;
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

import seedu.flashnotes.logic.commands.AddCommand;
import seedu.flashnotes.logic.commands.AddDeckCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.CorrectCommand;
import seedu.flashnotes.logic.commands.DeleteCommand;
import seedu.flashnotes.logic.commands.DeleteDeckCommand;
import seedu.flashnotes.logic.commands.EditCommand;
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
    public void homeScreen_add_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(AddCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_edit_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(EditCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_delete_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(DeleteCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_review_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(ReviewCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_flip_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(FlipCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_correct_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(CorrectCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_wrong_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
            -> parser.parseCommand(WrongCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void homeScreen_endReview_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_HOME, ()
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
        AddCommand command = (AddCommand) parser
                .parseCommand(FlashcardUtil.getAddCommand(flashcard), isNotReviewMode, isInDeck, DEFAULT_TAG);
        assertEquals(new AddCommand(flashcard), command);
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
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased(),
                isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void inDeck_edit_success() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(flashcard).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor), isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new EditCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
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
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isNotReviewMode,
                        isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_deleteDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_enterDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
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
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(ClearCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_list_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_flip_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(FlipCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_correct_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(CorrectCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_wrong_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
            -> parser.parseCommand(WrongCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void inDeck_endReview_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
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
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_deleteDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_enterDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(EnterDeckCommand.COMMAND_WORD + " " + keyword, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_list_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_clear_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(ClearCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_find_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(FindCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_setReviewLimit_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(SetReviewLimitCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_exit_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(ExitCommand.COMMAND_WORD, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_add_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(AddCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_edit_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(EditCommand.COMMAND_WORD,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void inReview_delete_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
            -> parser.parseCommand(DeleteCommand.COMMAND_WORD,
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
}
