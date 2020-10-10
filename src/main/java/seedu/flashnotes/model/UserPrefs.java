package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.flashnotes.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private static final int DEFAULT_CARD_LIMIT = 0;
    private GuiSettings guiSettings = new GuiSettings();
    private Path flashNotesFilePath = Paths.get("data" , "flashnotes.json");
    private Integer reviewCardLimit = DEFAULT_CARD_LIMIT;

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setFlashNotesFilePath(newUserPrefs.getFlashNotesFilePath());
        setReviewCardLimit(newUserPrefs.getReviewCardLimit());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFlashNotesFilePath() {
        return flashNotesFilePath;
    }

    public void setFlashNotesFilePath(Path flashNotesFilePath) {
        requireNonNull(flashNotesFilePath);
        this.flashNotesFilePath = flashNotesFilePath;
    }

    public Integer getReviewCardLimit() {
        return reviewCardLimit;
    }

    public void setReviewCardLimit(Integer reviewCardLimit) {
        requireNonNull(reviewCardLimit);
        this.reviewCardLimit = reviewCardLimit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && flashNotesFilePath.equals(o.flashNotesFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, flashNotesFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + flashNotesFilePath);
        return sb.toString();
    }

}
