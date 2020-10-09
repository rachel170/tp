package seedu.flashnotes.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.testutil.FlashcardBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different flashcard -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("GET1025"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("GET1025", "philosophy").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("GET1025", "philosophy"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("GET1025", "philosophy").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("GET1025", "philosophy"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("GET1025", "scifi").build()));

    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withTags("GET1025").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("GET1025"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("GET1035", "philosophy").build()));

        // Keywords match answer and question, but does not match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Main").withAnswer("12345").withTags("GET1025")
                .build()));

        //Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("get1025", "Philosophy"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("GET1025", "philosophy").build()));
    }
}
