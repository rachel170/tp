package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DESC_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DESC_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_NATURE;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;
import seedu.flashnotes.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCardCommandTest {

    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Flashcard editedFlashcard = new FlashcardBuilder().build();
        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard)
                .build();
        EditCardCommand editCommand = new EditCardCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new FlashNotes(model.getFlashNotes()), new UserPrefs());
        model.setIsInDeckTrue();
        model.setCurrentDeckName(FlashcardBuilder.DEFAULT_TAG);
        expectedModel.setCurrentDeckName(FlashcardBuilder.DEFAULT_TAG);
        expectedModel.setIsInDeckTrue();
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);
        expectedModel.addDeck(new Deck(FlashcardBuilder.DEFAULT_TAG));
        expectedModel.updateFilteredFlashcardList(new TagContainsKeywordsPredicate(FlashcardBuilder.DEFAULT_TAG));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFlashcard = Index.fromOneBased(model.getFilteredFlashcardList().size());
        Flashcard lastFlashcard = model.getFilteredFlashcardList().get(indexLastFlashcard.getZeroBased());

        FlashcardBuilder flashcardInList = new FlashcardBuilder(lastFlashcard);
        Flashcard editedFlashcard = flashcardInList.withQuestion(VALID_QUESTION_MACROECONS)
                .withAnswer(VALID_ANSWER_MACROECONS)
                .withTag(VALID_TAG_NATURE).build();

        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_MACROECONS)
                .withAnswer(VALID_ANSWER_MACROECONS).withTag(VALID_TAG_NATURE).build();
        EditCardCommand editCommand = new EditCardCommand(indexLastFlashcard, descriptor);

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new FlashNotes(model.getFlashNotes()), new UserPrefs());
        expectedModel.setFlashcard(lastFlashcard, editedFlashcard);

        model.setIsInDeckTrue();
        model.setCurrentDeckName(VALID_TAG_NATURE);
        expectedModel.setCurrentDeckName(VALID_TAG_NATURE);
        expectedModel.setIsInDeckTrue();
        expectedModel.addDeck(new Deck(VALID_TAG_NATURE));
        expectedModel.updateFilteredFlashcardList(new TagContainsKeywordsPredicate(VALID_TAG_NATURE));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidFlashcardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        EditCardCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_MACROECONS).build();
        EditCardCommand editCardCommand = new EditCardCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCardCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of flashnotes
     */
    @Test
    public void execute_invalidFlashcardIndexFilteredList_failure() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of flashnotes list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashNotes().getFlashcardList().size());

        EditCardCommand editCardCommand = new EditCardCommand(outOfBoundIndex,
                new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_MACROECONS).build());

        assertCommandFailure(editCardCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCardCommand standardCommand = new EditCardCommand(INDEX_FIRST_FLASHCARD, DESC_SKY);

        // same values -> returns true
        EditCardCommand.EditFlashcardDescriptor copyDescriptor = new EditCardCommand.EditFlashcardDescriptor(DESC_SKY);
        EditCardCommand commandWithSameValues = new EditCardCommand(INDEX_FIRST_FLASHCARD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCardCommand(INDEX_SECOND_FLASHCARD, DESC_SKY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCardCommand(INDEX_FIRST_FLASHCARD, DESC_MACROECONS)));
    }

}
