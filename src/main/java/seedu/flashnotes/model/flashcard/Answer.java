package seedu.flashnotes.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.AppUtil.checkArgument;

/**
 * Represents a flashcard's answer.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnswer(String)}
 */
public class Answer {

    public static final String MESSAGE_CONSTRAINTS =
            "Answers should not be blank and should less than or equal to 140 characters. "
                + "Current length of answer is %d.";
    public final String value;

    /**
     * Constructs a {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        value = answer;
    }

    /**
     * Returns true if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return !test.isBlank() && test.length() <= 140;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Answer // instanceof handles nulls
                && value.equals(((Answer) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
