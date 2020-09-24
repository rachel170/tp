package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.WHAT;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(WHAT.isSamePerson(WHAT));

        // null -> returns false
        assertFalse(WHAT.isSamePerson(null));

        // different answer -> returns false
        Person editedAlice = new PersonBuilder(WHAT).withAnswer(VALID_ANSWER_BOB).build();
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
        Person aliceCopy = new PersonBuilder(WHAT).build();
        assertTrue(WHAT.equals(aliceCopy));

        // same object -> returns true
        assertTrue(WHAT.equals(WHAT));

        // null -> returns false
        assertFalse(WHAT.equals(null));

        // different type -> returns false
        assertFalse(WHAT.equals(5));

        // different person -> returns false
        assertFalse(WHAT.equals(BOB));

        // different question -> returns false
        Person editedAlice = new PersonBuilder(WHAT).withQuestion(VALID_QUESTION_BOB).build();
        assertFalse(WHAT.equals(editedAlice));

        // different answer -> returns false
        editedAlice = new PersonBuilder(WHAT).withAnswer(VALID_ANSWER_BOB).build();
        assertFalse(WHAT.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new PersonBuilder(WHAT).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(WHAT.equals(editedAlice));
    }
}
