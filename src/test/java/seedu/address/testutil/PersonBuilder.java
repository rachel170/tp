package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Question;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_QUESTION = "Who am I?";
    public static final String DEFAULT_ANSWER = "85355255";

    private Question question;
    private Answer answer;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code flashcardToCopy}.
     */
    public PersonBuilder(Flashcard flashcardToCopy) {
        question = flashcardToCopy.getQuestion();
        answer = flashcardToCopy.getAnswer();
        tags = new HashSet<>(flashcardToCopy.getTags());
    }

    /**
     * Sets the {@code Question} of the {@code Flashcard} that we are building.
     */
    public PersonBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Flashcard} that we are building.
     */
    public PersonBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(question, answer, tags);
    }

}
