package seedu.flashnotes.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.flashnotes.commons.core.LogsCenter;

import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class IndividualFlashcard extends UiPart<Region> {
    private static final String FXML = "IndividualFlashcard.fxml"; //havent created this
    private final Logger logger = LogsCenter.getLogger(IndividualFlashcard.class);

    /**
     * Update these below to match flashcard.
     */
    @FXML
    private Text question;

    @FXML
    private Text answer;

    public IndividualFlashcard() {
        super(FXML);
    }
}
