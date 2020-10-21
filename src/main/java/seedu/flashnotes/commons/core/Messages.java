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

    // Messages for illegal commands used in home screen
    public static final String ILLEGAL_ADDCARD_COMMAND_IN_HOME_MESSAGE = "You cannot add a flashcard in the home" +
            " screen. Enter a deck by using the command `enterDeck n/DECKNAME` first.";
    public static final String ILLEGAL_EDITCARD_COMMAND_IN_HOME_MESSAGE = "You cannot edit a flashcard in the home" +
            " screen. Enter a deck by using the command `enterDeck n/DECKNAME` first.";
    public static final String ILLEGAL_DELETECARD_COMMAND_IN_HOME_MESSAGE = "You cannot delete a flashcard in the" +
            " home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first.";
    public static final String ILLEGAL_FIND_COMMAND_IN_HOME_MESSAGE = "You cannot find a flashcard in the" +
            " home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first.";
    public static final String ILLEGAL_REVIEW_COMMAND_IN_HOME_MESSAGE = "You cannot review a deck in the" +
            " home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first.";
    public static final String ILLEGAL_CORRECT_COMMAND_IN_HOME_MESSAGE = "You cannot mark a flashcard as correct " +
            "in the home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first and then enter " +
            "the review mode with the command `review`.";
    public static final String ILLEGAL_FLIP_COMMAND_IN_HOME_MESSAGE = "You cannot flip a flashcard " +
            "in the home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first and then enter " +
            "the review mode with the command `review`.";
    public static final String ILLEGAL_HOME_COMMAND_IN_HOME_MESSAGE = "You are already in the home screen.";
    public static final String ILLEGAL_WRONG_COMMAND_IN_HOME_MESSAGE = "You cannot mark a flashcard as wrong " +
            "in the home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first and then enter " +
            "the review mode with the command `review`.";
    public static final String ILLEGAL_ENDREVIEW_COMMAND_IN_HOME_MESSAGE = "You cannot end a review " +
            "in the home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first and then enter " +
            "the review mode with the command `review`.";

    // Messages for illegal commands used in review mode
    public static final String ILLEGAL_ADDCARD_COMMAND_IN_REVIEW_MESSAGE = "You cannot add a flashcard in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_EDITCARD_COMMAND_IN_REVIEW_MESSAGE = "You cannot edit a flashcard in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_DELETECARD_COMMAND_IN_REVIEW_MESSAGE = "You cannot delete a flashcard in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_CLEAR_COMMAND_IN_REVIEW_MESSAGE = "You cannot clear flashcards in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_FIND_COMMAND_IN_REVIEW_MESSAGE = "You cannot find flashcards in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE = "You cannot set review limit in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE = "You cannot enter a deck in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_LIST_COMMAND_IN_REVIEW_MESSAGE = "You cannot list flashcards in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_ADDDECK_COMMAND_IN_REVIEW_MESSAGE = "You cannot add a deck in the" +
            " review screen. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_EXIT_COMMAND_IN_REVIEW_MESSAGE = "You cannot exit review mode with this" +
            " command. Exit review mode by using the command `endReview` first.";
    public static final String ILLEGAL_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE = "You cannot delete a deck in the" +
            " review screen. Exit review mode by using the command `endReview` first.";

}
