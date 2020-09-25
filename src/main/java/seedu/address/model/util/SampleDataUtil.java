package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Question;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new Question("When was the last time you tried something new?"),
                    new Answer("yesterday"), getTagSet("friends")),
            new Flashcard(new Question("What’s the most sensible thing you’ve ever heard someone say?"),
                    new Answer("YOLO"), getTagSet("colleagues", "friends")),
            new Flashcard(new Question("What gets you excited about life?"),
                    new Answer("My phone"), getTagSet("neighbours")),
            new Flashcard(new Question("What do you wish you spent more time doing five years ago?"),
                    new Answer("Watching drama"), getTagSet("family")),
            new Flashcard(new Question("Do you ask enough questions or do you settle for what you know?"),
                    new Answer("nope, too lazy"), getTagSet("classmates")),
            new Flashcard(new Question("Who do you love and what are you doing about it?"), new Answer("Drama XD"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
