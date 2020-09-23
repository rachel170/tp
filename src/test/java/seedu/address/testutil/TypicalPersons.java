package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person WHO = new PersonBuilder().withQuestion("Who")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person WHAT = new PersonBuilder().withQuestion("What")
            .withPhone("98765432").withTags("owesMoney", "friends").build();
    public static final Person WHY = new PersonBuilder().withQuestion("Why").withPhone("95352563").build();
    public static final Person HOW = new PersonBuilder().withQuestion("How").withPhone("87652533")
            .withTags("friends").build();
    public static final Person WHEN = new PersonBuilder().withQuestion("When").withPhone("9482224").build();
    public static final Person WHERE = new PersonBuilder().withQuestion("Where").withPhone("9482427").build();
    public static final Person HOWMUCH = new PersonBuilder().withQuestion("How much").withPhone("9482442").build();

    // Manually added
    public static final Person WHOMEIER = new PersonBuilder().withQuestion("who is meier").withPhone("8482424").build();
    public static final Person WHATMEIER = new PersonBuilder().withQuestion("what is meier").withPhone("8482131").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withQuestion(VALID_QUESTION_AMY).withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withQuestion(VALID_QUESTION_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(WHO, WHAT, WHY, HOW, WHEN, WHERE, HOWMUCH));
    }
}
