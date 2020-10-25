package seedu.flashnotes.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNAVAILABLE_IN_REVIEW_MODE = "This command is not available in review mode. "
            + "Please exit the review mode by typing 'endReview' and try again.";
    public static final String MESSAGE_ALREADY_IN_REVIEW_MODE = "You are already in review mode.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX = "The flashcard index provided is invalid";
    public static final String MESSAGE_INVALID_COMMAND_IN_HOME = "Cannot call command in home screen.";
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "The deck index provided is invalid";
    public static final String MESSAGE_INVALID_COMMAND_IN_CARD = "Cannot call command in card screen.";
    public static final String MESSAGE_FLASHCARDS_LISTED_OVERVIEW = "Inside Deck. %1$d flashcards listed!";
    public static final String MESSAGE_DECK_OVERVIEW = "Back Home.";
    public static final String MESSAGE_INVALID_LIMIT = "Review card limit must be an integer greater than 0.";
    public static final String MESSAGE_NO_CARDS_TO_REVIEW = "Cannot review an empty deck! Add cards using "
            + "'add q/QUESTION a/ANSWER' first.";
}
