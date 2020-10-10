package seedu.flashnotes.testutil;

import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard WHO = new FlashcardBuilder().withQuestion("Who")
            .withAnswer("me")
            .withTag("friends").build();
    public static final Flashcard WHAT = new FlashcardBuilder().withQuestion("What")
            .withAnswer("idk").withTag("owesMoney").build();
    public static final Flashcard WHY = new FlashcardBuilder().withQuestion("Why")
            .withAnswer("because someone has to")
            .withTag("friends").build();
    public static final Flashcard HOW = new FlashcardBuilder().withQuestion("How").withAnswer("use your hands")
            .withTag("friends").build();
    public static final Flashcard WHEN = new FlashcardBuilder().withQuestion("When").withAnswer("at 2pm")
            .withTag("Time")
            .build();
    public static final Flashcard WHERE = new FlashcardBuilder().withQuestion("Where").withAnswer("at home")
            .withTag("Place").build();
    public static final Flashcard HOW_MUCH = new FlashcardBuilder().withQuestion("How much")
            .withAnswer("300 apples").withTag("Math").build();

    // Manually added
    public static final Flashcard WHO_IS_MEIER = new FlashcardBuilder().withQuestion("who is meier")
            .withAnswer("8482424").withTag("test").build();
    public static final Flashcard WHAT_IS_MEIER = new FlashcardBuilder().withQuestion("what is meier")
            .withAnswer("8482131").withTag("test").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new FlashcardBuilder().withQuestion(VALID_QUESTION_AMY)
            .withAnswer(VALID_ANSWER_AMY)
            .withTag(VALID_TAG_FRIEND).build();
    public static final Flashcard BOB = new FlashcardBuilder().withQuestion(VALID_QUESTION_BOB)
            .withAnswer(VALID_ANSWER_BOB)
            .withTag(VALID_TAG_HUSBAND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code FlashNotes} with all the typical flashcards.
     */
    public static FlashNotes getTypicalFlashNotes() {
        FlashNotes ab = new FlashNotes();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            ab.addFlashcard(flashcard);
        }

        //TODO used to set the Deck, remove after proper deck impl done - PX
        FlashNotes a = new FlashNotes(ab);
        return a;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(WHO, WHAT, WHY, HOW, WHEN, WHERE, HOW_MUCH));
    }
}
