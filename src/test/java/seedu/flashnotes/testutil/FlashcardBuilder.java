package seedu.flashnotes.testutil;


import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_QUESTION = "Who am I?";
    public static final String DEFAULT_ANSWER = "85355255";
    public static final String DEFAULT_TAG = "Singapore";

    private Question question;
    private Answer answer;
    private Tag tag;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        question = flashcardToCopy.getQuestion();
        answer = flashcardToCopy.getAnswer();
        tag = flashcardToCopy.getTag();
    }

    /**
     * Sets the {@code Question} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, tag);
    }

}
