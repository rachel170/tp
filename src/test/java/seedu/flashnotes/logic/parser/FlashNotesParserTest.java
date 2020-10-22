package seedu.flashnotes.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_EXTENDED_COMMAND_ERROR;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.FlashcardBuilder.DEFAULT_TAG;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.AddCommand;
import seedu.flashnotes.logic.commands.ClearCommand;
import seedu.flashnotes.logic.commands.DeleteCommand;
import seedu.flashnotes.logic.commands.EditCommand;
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
        AddCommand command = (AddCommand) parser
                .parseCommand(FlashcardUtil.getAddCommand(flashcard), isReviewMode, isInDeck, DEFAULT_TAG);
        assertEquals(new AddCommand(flashcard), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        // Clear command created by default
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, isReviewMode, !isInDeck, DEFAULT) instanceof ClearCommand);
        // Throws ParseException if Clear command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ClearCommand.COMMAND_WORD), ()
            -> parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", isReviewMode, !isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased(),
                isReviewMode, isInDeck, DEFAULT);
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Flashcard flashcard = new FlashcardBuilder().build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(flashcard).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashcardUtil.getEditFlashcardDescriptorDetails(descriptor), isReviewMode, isInDeck, DEFAULT);
        assertEquals(new EditCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        // Able to create ExitCommand by default
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, isReviewMode, isInDeck, DEFAULT) instanceof ExitCommand);
        // Throws ParseException if Exit command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ExitCommand.COMMAND_WORD), ()
            -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", isReviewMode, isInDeck, DEFAULT));
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
        // Able to create HelpCommand by default
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, isReviewMode, isInDeck, DEFAULT) instanceof HelpCommand);
        // Throws ParseException if Help command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, HelpCommand.COMMAND_WORD), ()
            -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", isReviewMode, isInDeck, DEFAULT));
    }

    @Test
    public void parseCommand_list() throws Exception {
        // Able to create ListCommand by default
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, isReviewMode, !isInDeck, DEFAULT) instanceof ListCommand);
        // Throws ParseException if List command has any arguments
        assertThrows(ParseException.class, String.format(MESSAGE_EXTENDED_COMMAND_ERROR, ListCommand.COMMAND_WORD), ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3", isReviewMode, !isInDeck, DEFAULT));
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
}
