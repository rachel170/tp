package seedu.flashnotes.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.model.deck.Deck;

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
        resultStatistic = source.getResultStatistics();
    }

    /**
     * Reads from this Jackson-friendly adapted deck object, and turn it into model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Deck toModelType() throws IllegalValueException {
        // Check to make sure deckName is valid
        if (!Deck.isValidDeckLength(deckName)) {
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_LENGTH);
        } else if (!Deck.isValidDeckReservedName(deckName)) {
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_RESERVED);
        }

        //If deckName is valid, create a new Deck object with it
        Deck theDeck = new Deck(deckName);

        // Check to make sure resultStatistic is valid
        if (resultStatistic.isBlank()) {
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
        }
        try {
            // Check to make sure result statistics can be converted to Double
            double statistic = Double.parseDouble(resultStatistic);
            // Check to make sure that statistic is a valid value
            if (statistic >= 0.0 && statistic <= 100.0) {
                // Update the created Deck object with the retrieved result statistics
                theDeck.setResultStatistics(String.format("%.1f", statistic));
            } else {
                // Throw an exception
                throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
            }
        } catch (NumberFormatException nfe) {
            // If exception is found, throw IVE
            throw new IllegalValueException(Deck.MESSAGE_CONSTRAINTS_STATISTIC);
        }

        return theDeck;
    }

}
