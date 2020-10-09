package seedu.flashnotes.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.logic.Logic;
import seedu.flashnotes.logic.commands.CommandResult;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.logic.parser.exceptions.ParseException;

import java.util.logging.Logger;

public class FlashcardListRoot extends UiPart<Region> implements RootNode{
    private static final String FXML = "FlashcardListRoot.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    protected final Stage primaryStage;
    protected final Logic logic;

    // Independent Ui parts residing in this Ui container
    protected FlashcardListPanel flashcardListPanel;
    protected ResultDisplay resultDisplay;
    protected HelpWindow helpWindow;

    @FXML
    protected StackPane commandBoxPlaceholder;

    @FXML
    protected MenuItem helpMenuItem;

    @FXML
    protected StackPane cardListPanelPlaceholder;

    @FXML
    protected StackPane resultDisplayPlaceholder;

    @FXML
    protected StackPane statusbarPlaceholder;
    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public FlashcardListRoot(Stage primaryStage, Logic logic) {
        super(FXML);

        this.primaryStage = primaryStage;
        this.logic = logic;
        setAccelerators();

        helpWindow = new HelpWindow();
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }
    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        flashcardListPanel = new FlashcardListPanel(logic.getFilteredFlashcardList());
        cardListPanelPlaceholder.getChildren().add(flashcardListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFlashNotesFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    @Override
    public void setFeedbackToUser(String s) {
        resultDisplay.setFeedbackToUser(s);
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
    }

    public FlashcardListPanel getFlashcardListPanel() {
        return flashcardListPanel;
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

            if (commandResult.isHome()) {
                RootNode rootNode = new DeckCardListRoot(primaryStage, logic);

                Region root = rootNode.getFxmlLoader().getRoot();
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

                rootNode.fillInnerParts();
                rootNode.setFeedbackToUser(commandResult.getFeedbackToUser());
            }
            if (commandResult.isDeck()) {
                throw new CommandException("Already in the Deck Screen! Navigate back to Home screen first!");

            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}

