package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Question;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditFlashcardDescriptor objects.
 */
public class EditFlashcardDescriptorBuilder {

    private EditCommand.EditFlashcardDescriptor descriptor;

    public EditFlashcardDescriptorBuilder() {
        descriptor = new EditCommand.EditFlashcardDescriptor();
    }

    public EditFlashcardDescriptorBuilder(EditCommand.EditFlashcardDescriptor descriptor) {
        this.descriptor = new EditCommand.EditFlashcardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFlashcardDescriptor} with fields containing {@code flashcard}'s details
     */
    public EditFlashcardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditCommand.EditFlashcardDescriptor();
        descriptor.setQuestion(flashcard.getQuestion());
        descriptor.setAnswer(flashcard.getAnswer());
        descriptor.setTags(flashcard.getTags());
    }

    /**
     * Sets the {@code Question} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new Question(question));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFlashcardDescriptor}
     * that we are building.
     */
    public EditFlashcardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditFlashcardDescriptor build() {
        return descriptor;
    }
}
