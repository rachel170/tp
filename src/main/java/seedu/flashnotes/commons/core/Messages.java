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
    public static final String MESSAGE_INVALID_DECK_DISPLAYED_INDEX = "The deck index provided is invalid.";
    public static final String MESSAGE_INVALID_COMMAND_IN_CARD = "Cannot call command in card screen.";
    public static final String MESSAGE_FLASHCARDS_LISTED_OVERVIEW = "Inside Deck. %1$d flashcards listed!";
    public static final String MESSAGE_DECK_OVERVIEW = "Back Home.";

    // Messages for invalid commands used in home screen
    public static final String INVALID_ADDCARD_COMMAND_IN_HOME_MESSAGE = "You cannot add a flashcard in the home"
            + " screen. Enter a deck by using the command `enterDeck DECKNAME` first.";
    public static final String INVALID_EDITCARD_COMMAND_IN_HOME_MESSAGE = "You cannot edit a flashcard in the home"
            + " screen. Enter a deck by using the command `enterDeck DECKNAME` first.";
    public static final String INVALID_DELETECARD_COMMAND_IN_HOME_MESSAGE = "You cannot delete a flashcard in the"
            + " home screen. Enter a deck by using the command `enterDeck DECKNAME` first.";
    public static final String INVALID_FIND_COMMAND_IN_HOME_MESSAGE = "You cannot find a flashcard in the"
            + " home screen. Enter a deck by using the command `enterDeck DECKNAME` first.";
    public static final String INVALID_REVIEW_COMMAND_IN_HOME_MESSAGE = "You cannot review a deck in the"
            + " home screen. Enter a deck by using the command `enterDeck DECKNAME` first.";
    public static final String INVALID_CORRECT_COMMAND_IN_HOME_MESSAGE = "You cannot mark a flashcard as correct "
            + "in the home screen. Enter a deck by using the command `enterDeck DECKNAME` first and then enter "
            + "the review mode with the command `review`.";
    public static final String INVALID_FLIP_COMMAND_IN_HOME_MESSAGE = "You cannot flip a flashcard "
            + "in the home screen. Enter a deck by using the command `enterDeck DECKNAME` first and then enter "
            + "the review mode with the command `review`.";
    public static final String INVALID_HOME_COMMAND_IN_HOME_MESSAGE = "You are already in the home screen.";
    public static final String INVALID_WRONG_COMMAND_IN_HOME_MESSAGE = "You cannot mark a flashcard as wrong "
            + "in the home screen. Enter a deck by using the command `enterDeck n/DECKNAME` first and then enter "
            + "the review mode with the command `review`.";
    public static final String INVALID_ENDREVIEW_COMMAND_IN_HOME_MESSAGE = "You cannot end a review "
            + "in the home screen. Enter a deck by using the command `enterDeck DECKNAME` first and then enter "
            + "the review mode with the command `review`.";

    // Messages for invalid commands used in review mode
    public static final String INVALID_ADDCARD_COMMAND_IN_REVIEW_MESSAGE = "You cannot add a flashcard in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_EDITCARD_COMMAND_IN_REVIEW_MESSAGE = "You cannot edit a flashcard in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_DELETECARD_COMMAND_IN_REVIEW_MESSAGE = "You cannot delete a flashcard in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_CLEAR_COMMAND_IN_REVIEW_MESSAGE = "You cannot clear flashcards in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_FIND_COMMAND_IN_REVIEW_MESSAGE = "You cannot find flashcards in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_SETREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE = "You cannot set review limit in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_ENTERDECK_COMMAND_IN_REVIEW_MESSAGE = "You cannot enter a deck in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_LIST_COMMAND_IN_REVIEW_MESSAGE = "You cannot list flashcards in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_ADDDECK_COMMAND_IN_REVIEW_MESSAGE = "You cannot add a deck in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_EXIT_COMMAND_IN_REVIEW_MESSAGE = "You cannot exit review mode with this"
            + " command. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_DELETEDECK_COMMAND_IN_REVIEW_MESSAGE = "You cannot delete a deck in the"
            + " review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_CHECKREVIEWLIMIT_COMMAND_IN_REVIEW_MESSAGE = "You cannot check review "
            + "limit in the review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_EDITDECKNAME_COMMAND_IN_REVIEW_MESSAGE = "You cannot edit deck name in "
            + "the review screen. Exit review mode by using the command `endReview` first.";
    public static final String INVALID_HOME_COMMAND_IN_REVIEW_MESSAGE = "You cannot enter home command in "
            + "the review screen. Exit review mode by using the command `endReview` first.";

    // Messages for invalid commands used in deck screen
    public static final String INVALID_ADDDECK_COMMAND_IN_DECK_MESSAGE = "You cannot add a deck in the"
            + " deck screen. Exit deck mode by using the command `home` first.";
    public static final String INVALID_CLEAR_COMMAND_IN_DECK_MESSAGE = "You cannot clear decks in the"
            + " deck screen. Exit deck mode by using the command `home` first.";
    public static final String INVALID_CORRECT_COMMAND_IN_DECK_MESSAGE = "You cannot mark a flashcard as correct "
            + "in the deck screen. Enter the review mode with the command `review`.";
    public static final String INVALID_DELETEDECK_COMMAND_IN_DECK_MESSAGE = "You cannot delete a deck in the"
            + " deck screen. Exit deck mode by using the command `home` first.";
    public static final String INVALID_ENTERDECK_COMMAND_IN_DECK_MESSAGE = "You are already in the"
            + " deck screen. If you want to view another deck, exit deck mode by using the command `home` first.";
    public static final String INVALID_FLIP_COMMAND_IN_DECK_MESSAGE = "You cannot flip a flashcard "
            + "in the deck screen. Enter the review mode with the command `review`.";
    public static final String INVALID_LIST_COMMAND_IN_DECK_MESSAGE = "You cannot list in the"
            + " deck screen. Exit deck mode by using the command `home` first.";
    public static final String INVALID_ENDREVIEW_COMMAND_IN_DECK_MESSAGE = "You cannot end a review "
            + "in the deck screen. Enter the review mode with the command `review`.";
    public static final String INVALID_WRONG_COMMAND_IN_DECK_MESSAGE = "You cannot mark a flashcard as wrong "
            + "in the deck screen. Enter the review mode with the command `review`.";
    public static final String INVALID_EDITDECKNAME_COMMAND_IN_DECK_MESSAGE = "You cannot edit deck name in the deck"
            + " mode. Exit deck mode by using the command `home` first.";

    // Other messages
    public static final String MESSAGE_EXTENDED_COMMAND_ERROR = "This command contains more arguments than necessary. "
            + "\nPlease try the command again without any arguments: %1$s";
    public static final String MESSAGE_INVALID_LIMIT = "Review card limit must be an integer greater than 0.";
    public static final String MESSAGE_NO_CARDS_TO_REVIEW = "Cannot review an empty deck! Add cards using "
            + "'addCard q/QUESTION a/ANSWER' first.";

}
