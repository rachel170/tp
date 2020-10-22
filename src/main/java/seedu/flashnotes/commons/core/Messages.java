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
    public static final String MESSAGE_FLASHCARDS_LISTED_OVERVIEW = "Inside Deck. %1$d flashcards listed!";
    public static final String MESSAGE_DECK_OVERVIEW = "Back Home.";
    public static final String MESSAGE_EXTENDED_COMMAND_ERROR = "This command contains more arguments than necessary. "
            + "\nPlease try the command again without any arguments: %1$s";

}
