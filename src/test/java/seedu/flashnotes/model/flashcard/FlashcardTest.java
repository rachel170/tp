package seedu.flashnotes.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_ECONOMICS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_NATURE;
import static seedu.flashnotes.testutil.TypicalFlashcards.MACROECONS;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void execute_flipFlashcard() {
        WHAT.flipFlashcard();
        assertEquals(true, WHAT.getIsFlipped());
    }

    @Test
    public void execute_doubleFlipFlashcard() {
        WHAT.flipFlashcard();
        WHAT.flipFlashcard();
        assertEquals(false, WHAT.getIsFlipped());
    }

    @Test
    public void execute_resetFlipBeforeFlipping() {
        WHAT.resetFlip();
        assertEquals(false, WHAT.getIsFlipped());
    }

    @Test
    public void execute_resetFlipAfterFlipping() {
        WHAT.flipFlashcard();
        WHAT.resetFlip();
        assertEquals(false, WHAT.getIsFlipped());
    }

    @Test
    public void execute_resetFlipAfterDoubleFlips() {
        WHAT.flipFlashcard();
        WHAT.flipFlashcard();
        WHAT.resetFlip();
        assertEquals(false, WHAT.getIsFlipped());
    }

    @Test
    public void execute_markCardAsCorrect_success() {
        WHAT.markCard(2);
        assertEquals(2, WHAT.getIsCorrect());
    }

    @Test
    public void execute_markCardAsWrong_success() {
        WHAT.markCard(1);
        assertEquals(1, WHAT.getIsCorrect());
    }

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(WHAT.isSameFlashcard(WHAT));

        // null -> returns false
        assertFalse(WHAT.isSameFlashcard(null));

        // different answer -> returns false
        Flashcard editedAlice = new FlashcardBuilder(WHAT).withAnswer(VALID_ANSWER_MACROECONS)
                .withTag(VALID_TAG_NATURE).build();
        assertFalse(WHAT.isSameFlashcard(editedAlice));

        // different question -> returns false
        editedAlice = new FlashcardBuilder(WHAT).withQuestion(VALID_QUESTION_MACROECONS)
                .withTag(VALID_TAG_NATURE).build();
        assertFalse(WHAT.isSameFlashcard(editedAlice));

        // same question, same answer, different attributes -> returns false
        editedAlice = new FlashcardBuilder(WHAT)
                .withTag(VALID_TAG_ECONOMICS).build();
        assertFalse(WHAT.isSameFlashcard(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard aliceCopy = new FlashcardBuilder(WHAT).build();
        assertTrue(WHAT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(WHAT.equals(WHAT));

        // null -> returns false
        assertFalse(WHAT.equals(null));

        // different type -> returns false
        assertFalse(WHAT.equals(5));

        // different flashcard -> returns false
        assertFalse(WHAT.equals(MACROECONS));

        // different question -> returns false
        Flashcard editedAlice = new FlashcardBuilder(WHAT).withQuestion(VALID_QUESTION_MACROECONS).build();
        assertFalse(WHAT.equals(editedAlice));

        // different answer -> returns false
        editedAlice = new FlashcardBuilder(WHAT).withAnswer(VALID_ANSWER_MACROECONS).build();
        assertFalse(WHAT.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new FlashcardBuilder(WHAT).withTag(VALID_TAG_NATURE).build();
        assertFalse(WHAT.equals(editedAlice));
    }
}
