package seedu.flashnotes.model.deck;

import seedu.flashnotes.model.flashcard.Flashcard;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Deck {
    // Identity fields
    private String deckName;
    private String resultStatistics;

    public Deck (String name) {
        requireNonNull(name);
        this.deckName = name;
        this.resultStatistics = "";
    }

    public String getDeckName() {
        return deckName;
    }

    public String getResultStatistics() {
        return resultStatistics;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setResultStatistics(String resultStatistics) {
        this.resultStatistics = resultStatistics;
    }

    /**
     * Returns true if both Decks have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two decks.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        return otherDeck != null
                && otherDeck.getDeckName().equals(getDeckName())
                && (otherDeck.getResultStatistics().equals(getResultStatistics()));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(deckName, deck.deckName) &&
                Objects.equals(resultStatistics, deck.resultStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckName, resultStatistics);
    }
}
