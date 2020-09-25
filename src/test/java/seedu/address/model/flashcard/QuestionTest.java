package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test
    public void constructor_invalidQuestion_throwsIllegalArgumentException() {
        String invalidQuestion = "";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidQuestion));
    }

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid question
        assertFalse(Question.isValidQuestion("")); // empty string
        assertFalse(Question.isValidQuestion(" ")); // spaces only
        assertFalse(Question.isValidQuestion("^")); // only non-alphanumeric characters
        assertFalse(Question.isValidQuestion("Why am I like this*")); // contains non-alphanumeric characters

        // valid question
        assertTrue(Question.isValidQuestion("who am I")); // alphabets only
        assertTrue(Question.isValidQuestion("12345")); // numbers only
        assertTrue(Question.isValidQuestion("where is peter the 2nd")); // alphanumeric characters
        assertTrue(Question.isValidQuestion("What is life")); // with capital letters
        assertTrue(Question.isValidQuestion("What is the definition of macroeconomics?")); // long questions
    }
}
