package seedu.flashnotes.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Deck}.
 */
class JsonAdaptedDeck {

    private final String deckName;
    // deck statistic
    private final String resultStatistic;

    /**
     * Constructs a {@code JsonAdaptedDeck} with the given FlashNote deck details.
     */
    @JsonCreator
    public JsonAdaptedDeck(@JsonProperty("deck") String deckName, @JsonProperty("statistic") String stat) {
        this.deckName = deckName;
        this.resultStatistic = stat;
    }

    /**
     * Converts a given {@code Deck} into this class for Jackson use.
     */
    public JsonAdaptedDeck(Deck source) {
        deckName = source.getDeckName();
        resultStatistic = source.getResultStatistics().toString();
    }

    /**
     * Reads from this Jackson-friendly adapted deck object, and turn it into model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public void updateModel(FlashNotes flashNotes) throws IllegalValueException {
        // Check to make sure deck name is valid
        if (!Deck.isValidDeck(deckName)) {
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS);
        }

        // Check to make sure resultStatistic is valid
        if (resultStatistic.isBlank()) {
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
        }
        try {
            Integer statistics = Integer.parseInt(resultStatistic);
            // Update the FlashNotes Model with resultStatistics
            flashNotes.updateDeckPerformanceScore(statistics, deckName);
        } catch (NumberFormatException nfe) {
            // If exception is found, throw IVE
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
        }
    }
    public Deck toModelType() throws IllegalValueException {
        if (!Deck.isValidDeck(deckName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        // Check to make sure resultStatistic is valid
        if (resultStatistic.isBlank()) {
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
        }
        try {
            // Check to make sure result statistics can be converted to Integer
            Integer.parseInt(resultStatistic);
        } catch (NumberFormatException nfe) {
            // If exception is found, throw IVE
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
        }
        Deck theDeck =  new Deck(deckName);
        theDeck.setResultStatistics(resultStatistic);

        return theDeck;
    }

}
