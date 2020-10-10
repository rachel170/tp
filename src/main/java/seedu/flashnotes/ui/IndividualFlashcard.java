package seedu.flashnotes.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.flashnotes.commons.core.LogsCenter;

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
