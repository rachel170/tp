package seedu.flashnotes.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.logic.Logic;
import seedu.flashnotes.logic.commands.CommandResult;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Controller for a help page
 */
public class ReviewWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ReviewWindow.class);
    private static final String FXML = "ReviewWindow.fxml";
    private Logic logic;
    private List<Flashcard> flashcardsToReview;
    private int index;
    private CommandBox commandBox;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private Stage primaryStage;

    @FXML
    private Text question;

    @FXML
    private Text answer;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a new ReviewWindow.
     */
    public ReviewWindow(Logic logic, Stage primaryStage) {
        super(FXML, new Stage());
        this.logic = logic;
        this.index = 0;
        this.helpWindow = new HelpWindow();
        this.commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        this.resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        this.primaryStage = primaryStage;
    }

    private void updateCurrentFlashcard() {
        this.flashcardsToReview = logic.getFlashcardsToReview();
        Flashcard flashcardToDisplay = flashcardsToReview.get(index);
        question.setText("Question: " + flashcardToDisplay.getQuestion().question);
        answer.setText("Answer: " + flashcardToDisplay.getAnswer().value);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        this.commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Shows the review window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing review page about the application.");
        updateCurrentFlashcard();
        question.setVisible(true);
        answer.setVisible(true);
        getRoot().setAlwaysOnTop(true);
        getRoot().showAndWait();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
        this.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.flashnotes.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
