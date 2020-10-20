package seedu.flashnotes.model.flashcard;

import static seedu.flashnotes.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.flashnotes.model.tag.Tag;

/**
 * Represents a Flashcard in the flashnotes book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Question question;
    private final Answer answer;
    private final Tag tag;
    private boolean isFlipped = false;
    private int isCorrect = 0;

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Question question, Answer answer, Tag tag) {
        requireAllNonNull(question, answer, tag);
        this.question = question;
        this.answer = answer;
        this.tag = tag;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Returns whether the flashcard has been flipped in review mode
     *
     * @return boolean
     */
    public boolean getIsFlipped() {
        return isFlipped;
    }

    /**
     * Flips flashcard being reviewed in the review mode
     */
    public void flipFlashcard() {
        if (this.isFlipped) {
            this.isFlipped = false;
        } else {
            this.isFlipped = true;
        }
    }

    /**
     * Resets the value of isFlipped to false
     */
    public void resetFlip() {
        isFlipped = false;
    }

    /**
     * Marks whether the card was reviewed correctly in the review mode
     * @param result
     */
    public void markCard(int result) {
        this.isCorrect = result;
    }

    /**
     * Returns the value of isCorrect
     * @return int
     */
    public int getIsCorrect() {
        return this.isCorrect;
    }

    /**
     * Returns true if both flashcards have the same question have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getQuestion().equals(getQuestion())
                && (otherFlashcard.getAnswer().equals(getAnswer()))
                && otherFlashcard.getTag().equals(getTag())
                && otherFlashcard.getIsFlipped() == getIsFlipped();
    }

    /**
     * Returns true if both cards have the same identity and data fields.
     * This defines a stronger notion of equality between two cards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Flashcard)) {
            return false;
        }

        Flashcard otherFlashcard = (Flashcard) other;
        return otherFlashcard.getQuestion().equals(getQuestion())
                && otherFlashcard.getAnswer().equals(getAnswer())
                && otherFlashcard.getTag().equals(getTag())
                && otherFlashcard.getIsFlipped() == getIsFlipped();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer, tag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Question: ")
                .append(getQuestion())
                .append(" Answer: ")
                .append(getAnswer())
                .append(" Tag: ")
                .append(getTag());
        return builder.toString();
    }

}
