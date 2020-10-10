package seedu.flashnotes.ui;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.model.deck.Deck;


public class DeckCardListPanel extends UiPart<Region> {
    private static final String FXML = "DeckCardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeckCardListPanel.class);

    @FXML
    private ListView<Deck> deckCardListView;

    /**
     * Creates a {@code DeckCardListPanel} with the given {@code ObservableList}.
     */
    public DeckCardListPanel(ObservableList<Deck> deckList) {
        super(FXML);
        deckCardListView.setItems(deckList);
        deckCardListView.setCellFactory(listView -> new DeckCardListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Deck} using a {@code DeckCard}.
     */
    class DeckCardListViewCell extends ListCell<Deck> {
        @Override
        protected void updateItem(Deck deck, boolean empty) {
            super.updateItem(deck, empty);

            if (empty || deck == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeckCard(deck, getIndex() + 1).getRoot());
            }
        }
    }

}
