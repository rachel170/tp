package seedu.flashnotes.model;

import java.nio.file.Path;

import seedu.flashnotes.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFlashNotesFilePath();

    Integer getReviewCardLimit();
}
