package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.WHAT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

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

        // different phone -> returns false
        Person editedAlice = new PersonBuilder(WHAT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(WHAT.isSamePerson(editedAlice));

        // different question -> returns false
        editedAlice = new PersonBuilder(WHAT).withQuestion(VALID_QUESTION_BOB).build();
        assertFalse(WHAT.isSamePerson(editedAlice));

        // same question, same phone, different attributes -> returns true
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

        // different phone -> returns false
        editedAlice = new PersonBuilder(WHAT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(WHAT.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new PersonBuilder(WHAT).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(WHAT.equals(editedAlice));
    }
}
