package seedu.flashnotes.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.*;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.FlashcardBuilder.DEFAULT_TAG;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.*;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.deck.Deck;
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
    private boolean isNotInDeck = false;
    private boolean isReviewMode = true;
    private boolean isNotReviewMode = false;

    //========================= Home mode testcases ===================================================================
    @Test
    public void parseCommand_homeScreen_addDeck_success() throws Exception {
        String keyword = "foo";
        Deck deck = new Deck(keyword);
        AddDeckCommand command = (AddDeckCommand) parser
                .parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isNotReviewMode,
                        isNotInDeck, DEFAULT);
        assertEquals(new AddDeckCommand(deck), command);
    }

    @Test
    public void parseCommand_homeScreen_deleteDeck_success() throws Exception {
        String keyword = "foo";
        Deck deck = new Deck(keyword);
        DeleteDeckCommand command = (DeleteDeckCommand) parser.parseCommand(
                DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isNotReviewMode, isNotInDeck, DEFAULT);
        assertEquals(new DeleteDeckCommand(deck), command);
    }

    @Test
    public void parseCommand_homeScreen_enterDeck_success() throws Exception {
        String keyword = "foo";
        EnterDeckCommand command = (EnterDeckCommand) parser.parseCommand(
                EnterDeckCommand.COMMAND_WORD + " " + keyword, isNotReviewMode, isNotInDeck, DEFAULT);
        assertEquals(new EnterDeckCommand(new TagContainsKeywordsPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_homeScreen_exit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_homeScreen_help_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_homeScreen_list_success() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD,
                isNotReviewMode, isNotInDeck, DEFAULT) instanceof ListCommand);
    }

    @Test
    public void parseCommand_homeScreen_clear_success() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, isNotReviewMode, isNotInDeck, DEFAULT)
                instanceof ClearCommand);
    }

//    @Test
//    public void parseCommand_homeScreen_find_success() throws Exception {
//      Not implemented yet
//    }

    @Test
    public void parseCommand_homeScreen_add_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_edit_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_delete_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_review_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_flip_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_correct_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_wrong_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_endReview_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_homeScreen_home_throwsParseException() throws Exception {

    }

//    @Test
//    public void parseCommand_homeScreen_setReviewLimit_throwsParseException() throws Exception {
//      Not implemented yet
//    }

    @Test
    public void parseCommand_homeScreen_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand("", isNotReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_homeScreen_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand", isNotReviewMode, isNotInDeck, DEFAULT));
    }

    //========================= Card mode testcases ===================================================================
    @Test
    public void parseCommand_inDeck_add_success() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        AddCommand command = (AddCommand) parser
                .parseCommand(FlashcardUtil.getAddCommand(flashcard), isNotReviewMode, isInDeck, DEFAULT_TAG);
        assertEquals(new AddCommand(flashcard), command);
    }

    @Test
    public void parseCommand_inDeck_delete_success() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased(),
                isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_inDeck_edit_success() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(flashcard).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor), isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new EditCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_inDeck_exit_success() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD,
                isNotReviewMode, isInDeck, DEFAULT) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_inDeck_find_success() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")),
                isNotReviewMode, isInDeck, DEFAULT);
        assertEquals(new FindCommand(new QuestionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_inDeck_help_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isNotReviewMode, isInDeck, DEFAULT)
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_inDeck_home_success() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_review_success() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_setReviewLimit_success() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_addDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
                -> parser.parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isNotReviewMode,
                        isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inDeck_deleteDeck_throwsParseException() throws Exception {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_IN_CARD, ()
                -> parser.parseCommand(DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inDeck_enterDeck_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_clear_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_list_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_flip_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_correct_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_wrong_throwsParseException() throws Exception {

    }

    @Test
    public void parseCommand_inDeck_endReview_throwsParseException() throws Exception {

    }


    @Test
    public void parseCommand_inDeck_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", isNotReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inDeck_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", isNotReviewMode, isInDeck, DEFAULT));
    }

    //========================= Review mode testcases =================================================================
    @Test
    public void parseCommand_inReview_flip_success() {

    }

    @Test
    public void parseCommand_inReview_correct_success() {

    }

    @Test
    public void parseCommand_inReview_wrong_success() {

    }

    @Test
    public void parseCommand_inReview_endReview_success() {

    }

    @Test
    public void parseCommand_inReview_help_success() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isReviewMode, isNotInDeck, DEFAULT)
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_inReview_review_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_addDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
                -> parser.parseCommand(AddDeckCommand.COMMAND_WORD + " " + keyword, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inReview_deleteDeck_throwsParseException() {
        String keyword = "foo";
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
                -> parser.parseCommand(DeleteDeckCommand.COMMAND_WORD + " " + keyword,
                isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inReview_enterDeck_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_list_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_clear_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_find_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_setReviewLimit_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_exit_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_IN_REVIEW_MODE, ()
                -> parser.parseCommand(ExitCommand.COMMAND_WORD, isReviewMode,
                isNotInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inReview_add_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_edit_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_delete_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_home_throwsParseException() {

    }

    @Test
    public void parseCommand_inReview_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand("", isReviewMode, isNotInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_inReview_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand", isReviewMode, isNotInDeck, DEFAULT));
    }
}
