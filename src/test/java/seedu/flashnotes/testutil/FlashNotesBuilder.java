package seedu.flashnotes.testutil;

import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * A utility class to help with building FlashNotes objects.
 * Example usage: <br>
 *     {@code FlashNotes ab = new FlashNotesBuilder().withFlashcard("John", "Doe").build();}
 */
public class FlashNotesBuilder {

    private FlashNotes flashNotes;

    public FlashNotesBuilder() {
        flashNotes = new FlashNotes();
    }

    public FlashNotesBuilder(FlashNotes flashNotes) {
        this.flashNotes = flashNotes;
    }

    /**a
     * Adds a new {@code Flashcard} to the {@code FlashNotes} that we are building.
     */
    public FlashNotesBuilder withFlashcard(Flashcard flashcard) {
        flashNotes.addFlashcard(flashcard);
        return this;
    }

    public FlashNotes build() {
        return flashNotes;
    }
}
