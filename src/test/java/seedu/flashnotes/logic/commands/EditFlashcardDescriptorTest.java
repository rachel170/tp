package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DESC_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DESC_SKY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_MACROECONS;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_NATURE;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.EditCardCommand.EditFlashcardDescriptor;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashcardDescriptor descriptorWithSameValues = new EditFlashcardDescriptor(DESC_SKY);
        assertTrue(DESC_SKY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SKY.equals(DESC_SKY));

        // null -> returns false
        assertFalse(DESC_SKY.equals(null));

        // different types -> returns false
        assertFalse(DESC_SKY.equals(5));

        // different values -> returns false
        assertFalse(DESC_SKY.equals(DESC_MACROECONS));

        // different question -> returns false
        EditFlashcardDescriptor editedAmy = new EditFlashcardDescriptorBuilder(DESC_SKY)
                .withQuestion(VALID_QUESTION_MACROECONS).build();
        assertFalse(DESC_SKY.equals(editedAmy));

        // different answer -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_SKY).withAnswer(VALID_ANSWER_MACROECONS).build();
        assertFalse(DESC_SKY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_SKY).withTag(VALID_TAG_NATURE).build();
        assertFalse(DESC_SKY.equals(editedAmy));
    }
}
