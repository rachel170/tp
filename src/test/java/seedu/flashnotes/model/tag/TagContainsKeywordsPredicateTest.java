package seedu.flashnotes.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.testutil.FlashcardBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeyword);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeyword);
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
                new TagContainsKeywordsPredicate("GET1025");
        assertTrue(predicate.test(new FlashcardBuilder().withTag("GET1025").build()));


    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate("");
        assertFalse(predicate.test(new FlashcardBuilder().withTag("GET1025").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate("GET1025");
        assertFalse(predicate.test(new FlashcardBuilder().withTag("GET1035").build()));

        // Keywords match answer and question, but does not match tag
        predicate = new TagContainsKeywordsPredicate("Main");
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Main").withAnswer("12345").withTag("GET1025")
                .build()));

        //Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate("get1025");
        assertFalse(predicate.test(new FlashcardBuilder().withTag("GET1025").build()));
    }
}
