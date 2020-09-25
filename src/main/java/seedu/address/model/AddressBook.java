package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueFlashcardList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueFlashcardList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setPersons(List<Flashcard> flashcards) {
        this.persons.setFlashcards(flashcards);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the address book.
     */
    public boolean hasPerson(Flashcard flashcard) {
        requireNonNull(flashcard);
        return persons.contains(flashcard);
    }

    /**
     * Adds a flashcard to the address book.
     * The flashcard must not already exist in the address book.
     */
    public void addPerson(Flashcard p) {
        persons.add(p);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the address book.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the address book.
     */
    public void setPerson(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        persons.setPerson(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Flashcard key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        //return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
        return persons.asUnmodifiableObservableList().toString();
    }

    @Override
    public ObservableList<Flashcard> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
