package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.BOB;
import static seedu.address.testutil.TypicalFlashcards.WHAT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FlashcardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Flashcard flashcard = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashcard.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(WHAT.isSamePerson(WHAT));

        // null -> returns false
        assertFalse(WHAT.isSamePerson(null));

        // different answer -> returns false
        Flashcard editedAlice = new PersonBuilder(WHAT).withAnswer(VALID_ANSWER_BOB).build();
        assertFalse(WHAT.isSamePerson(editedAlice));

        // different question -> returns false
        editedAlice = new PersonBuilder(WHAT).withQuestion(VALID_QUESTION_BOB).build();
        assertFalse(WHAT.isSamePerson(editedAlice));

        // same question, same answer, different attributes -> returns true
        editedAlice = new PersonBuilder(WHAT)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(WHAT.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard aliceCopy = new PersonBuilder(WHAT).build();
        assertTrue(WHAT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(WHAT.equals(WHAT));

        // null -> returns false
        assertFalse(WHAT.equals(null));

        // different type -> returns false
        assertFalse(WHAT.equals(5));

        // different flashcard -> returns false
        assertFalse(WHAT.equals(BOB));

        // different question -> returns false
        Flashcard editedAlice = new PersonBuilder(WHAT).withQuestion(VALID_QUESTION_BOB).build();
        assertFalse(WHAT.equals(editedAlice));

        // different answer -> returns false
        editedAlice = new PersonBuilder(WHAT).withAnswer(VALID_ANSWER_BOB).build();
        assertFalse(WHAT.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new PersonBuilder(WHAT).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(WHAT.equals(editedAlice));
    }
}
