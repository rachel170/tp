package seedu.flashnotes.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.flashnotes.commons.util.StringUtil;
import seedu.flashnotes.model.flashcard.Flashcard;

public class TagContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) { this.keywords = keywords; }
    @Override
    public boolean test(Flashcard flashcard) {
        String test = flashcard.getTags().toString();
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(test, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
