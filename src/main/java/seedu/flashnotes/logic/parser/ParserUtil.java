package seedu.flashnotes.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.commons.util.StringUtil;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Current index is not a non-zero unsigned integer "
            + "(less than 2147483648).";
    public static final String MESSAGE_INVALID_LIMIT_LARGE = "Current review limit is not a non-zero unsigned integer "
            + "(less than 2147483648).";
    public static final String MESSAGE_INVALID_LIMIT = "Review card limit must be an integer greater than 0.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code question} is invalid.
     */
    public static Question parseQuestion(String question) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!Question.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(String.format(Question.MESSAGE_CONSTRAINTS, question.length()));
        }
        return new Question(trimmedQuestion);
    }

    /**
     * Parses a {@code String answer} into a {@code Answer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code answer} is invalid.
     */
    public static Answer parseAnswer(String answer) throws ParseException {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim();
        if (!Answer.isValidAnswer(trimmedAnswer)) {
            throw new ParseException(String.format(Answer.MESSAGE_CONSTRAINTS, answer.length()));
        }
        return new Answer(trimmedAnswer);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }


    /**
     * Parses a review limit input and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified integer is invalid (not greater than 0).
     */
    public static long parseReviewLimit(String reviewLimitString) throws ParseException {
        String trimmedReviewLimit = reviewLimitString.trim().toLowerCase();
        if (trimmedReviewLimit.equals("all")) {
            return Integer.MAX_VALUE;
        } else {
            try {
                if (!StringUtil.isNonZeroUnsignedInteger(trimmedReviewLimit)) {
                    throw new ParseException(MESSAGE_INVALID_LIMIT_LARGE);
                }
                return (long) Long.parseLong(trimmedReviewLimit);
            } catch (NumberFormatException e) {
                throw new ParseException(MESSAGE_INVALID_LIMIT);
            }
        }
    }

    /**
     * Parses a {@code String deckName} into a {@code Deck}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deckName} is invalid.
     */
    public static Deck parseDeckName(String deckName) throws ParseException {
        requireNonNull(deckName);
        String tagDeckRelationNote = "\nNote that the cards' tag correspond to the deck they belong to.";
        String trimmedDeckName = deckName.trim();
        if (!Deck.isValidDeckLength(trimmedDeckName)) {
            throw new ParseException(String.format(Deck.MESSAGE_CONSTRAINTS_LENGTH
                    + tagDeckRelationNote, deckName.length()));
        } else if (!Deck.isValidDeckReservedName(trimmedDeckName)) {
            throw new ParseException(Deck.MESSAGE_CONSTRAINTS_RESERVED + tagDeckRelationNote);
        }
        return new Deck(trimmedDeckName);
    }
}
