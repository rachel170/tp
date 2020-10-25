package seedu.flashnotes.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.AppUtil.checkArgument;

/**
 * Represents a flashcard's question.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuestion(String)}
 */
public class Question {

    public static final String MESSAGE_CONSTRAINTS =
            "Questions should not be blank and should be fewer than 140 characters. "
                + "Current length of question is %d.";

    public final String question;

    /**
     * Constructs a {@code Question}.
     *
     * @param question A valid question.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
    }

    /**
     * Returns true if a given string is a valid question.
     */
    public static boolean isValidQuestion(String test) {
        return !test.isBlank() && test.length() <= 140;
    }

    @Override
    public String toString() {
        return this.question;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Question // instanceof handles nulls
                && this.question.equals(((Question) other).question)); // state check
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

}
