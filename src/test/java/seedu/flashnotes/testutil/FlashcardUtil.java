package seedu.flashnotes.testutil;

import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.flashnotes.logic.commands.AddCardCommand;
import seedu.flashnotes.logic.commands.EditCardCommand.EditFlashcardDescriptor;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * A utility class for Flashcard.
 */
public class FlashcardUtil {

    /**
     * Returns an add command string for adding the {@code flashcard}.
     */
    public static String getAddCardCommand(Flashcard flashcard) {
        return AddCardCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns the part of command string for the given {@code flashcard}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + flashcard.getQuestion().question + " ");
        sb.append(PREFIX_ANSWER + flashcard.getAnswer().value + " ");
        //sb.append(PREFIX_TAG + flashcard.getTag().tagName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFlashcardDescriptor}'s details.
     */
    public static String getEditFlashcardDescriptorDetails(EditFlashcardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getQuestion().ifPresent(question -> sb.append(PREFIX_QUESTION)
                .append(question.question).append(" "));
        descriptor.getAnswer().ifPresent(answer -> sb.append(PREFIX_ANSWER).append(answer.value).append(" "));
        descriptor.getTag().ifPresent(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
        return sb.toString();
    }
}
