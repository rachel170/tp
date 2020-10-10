package seedu.flashnotes.model.tag;

import java.util.function.Predicate;

import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Tests that a {@code Flashcard}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final String keyword;

    public TagContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return flashcard.getTag().tagName.equals(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((TagContainsKeywordsPredicate) other).keyword)); // state check
    }
}
