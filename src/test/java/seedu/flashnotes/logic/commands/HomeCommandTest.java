package seedu.flashnotes.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_DECK_OVERVIEW;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

public class HomeCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashNotes(), new UserPrefs());
    }

    @Test
    public void execute_homeCommand_showsAllDecks() {
        assertCommandSuccess(new HomeCommand(), model, MESSAGE_DECK_OVERVIEW, expectedModel);
    }

    @Test
    public void equals() {
        HomeCommand homeCommand = new HomeCommand();
        // same object -> returns true
        assertTrue(homeCommand.equals(homeCommand));

        // different types -> returns false
        assertFalse(homeCommand.equals(1));

        // null -> returns false
        assertFalse(homeCommand.equals(null));
    }
}
