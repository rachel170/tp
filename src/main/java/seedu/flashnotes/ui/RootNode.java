package seedu.flashnotes.ui;

import javafx.fxml.FXMLLoader;

public interface RootNode {

    void fillInnerParts();
    FXMLLoader getFxmlLoader();

    void setFeedbackToUser(String s);
}
