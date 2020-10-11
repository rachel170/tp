package seedu.flashnotes.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.logic.Logic;
import seedu.flashnotes.logic.commands.CommandResult;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.logic.parser.exceptions.ParseException;

/**
 * Controller for a help page
 */
public class ReviewWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ReviewWindow.class);
    private static final String FXML = "ReviewWindow.fxml";

    private Logic logic;
    private CommandBox commandBox;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private IndividualFlashcard individualFlashcard;

    private Stage primaryStage;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane individualFlashcardPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a new ReviewWindow.
     */
    public ReviewWindow(Logic logic, Stage primaryStage) {
        super(FXML, new Stage());
        this.logic = logic;
        this.helpWindow = new HelpWindow();
        this.primaryStage = primaryStage;

        this.commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        this.resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        this.individualFlashcard = new IndividualFlashcard(logic);
        individualFlashcardPlaceholder.getChildren().add(individualFlashcard.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFlashNotesFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
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
        this.individualFlashcard.init();
        this.individualFlashcard.displayFlashcard();
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
