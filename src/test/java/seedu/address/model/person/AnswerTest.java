package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class AnswerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test
    public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = "";
        assertThrows(IllegalArgumentException.class, () -> new Answer(invalidAnswer));
    }

    @Test
    public void isValidAnswer() {
        // null answer
        assertThrows(NullPointerException.class, () -> Answer.isValidAnswer(null));

        // invalid answers
        assertFalse(Answer.isValidAnswer("")); // empty string
        assertFalse(Answer.isValidAnswer(" ")); // space only
        assertFalse(Answer.isValidAnswer("a")); // 1 character
        assertFalse(Answer.isValidAnswer("b")); // 1 character
        assertFalse(Answer.isValidAnswer("c")); // 1 character
        assertFalse(Answer.isValidAnswer("d")); // 1 character

        // valid answers
        assertTrue(Answer.isValidAnswer("red"));
        assertTrue(Answer.isValidAnswer("blue"));
        assertTrue(Answer.isValidAnswer("green is the new yellow"));
    }
}
