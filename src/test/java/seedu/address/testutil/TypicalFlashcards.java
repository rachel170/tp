package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard WHO = new PersonBuilder().withQuestion("Who")
            .withAnswer("me")
            .withTags("friends").build();
    public static final Flashcard WHAT = new PersonBuilder().withQuestion("What")
            .withAnswer("idk").withTags("owesMoney", "friends").build();
    public static final Flashcard WHY = new PersonBuilder().withQuestion("Why")
            .withAnswer("because someone has to").build();
    public static final Flashcard HOW = new PersonBuilder().withQuestion("How").withAnswer("use your hands")
            .withTags("friends").build();
    public static final Flashcard WHEN = new PersonBuilder().withQuestion("When").withAnswer("at 2pm").build();
    public static final Flashcard WHERE = new PersonBuilder().withQuestion("Where").withAnswer("at home").build();
    public static final Flashcard HOWMUCH = new PersonBuilder().withQuestion("How much")
            .withAnswer("300 apples").build();

    // Manually added
    public static final Flashcard WHOMEIER = new PersonBuilder().withQuestion("who is meier")
            .withAnswer("8482424").build();
    public static final Flashcard WHATMEIER = new PersonBuilder().withQuestion("what is meier")
            .withAnswer("8482131").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new PersonBuilder().withQuestion(VALID_QUESTION_AMY)
            .withAnswer(VALID_ANSWER_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Flashcard BOB = new PersonBuilder().withQuestion(VALID_QUESTION_BOB)
            .withAnswer(VALID_ANSWER_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            ab.addPerson(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(WHO, WHAT, WHY, HOW, WHEN, WHERE, HOWMUCH));
    }
}
