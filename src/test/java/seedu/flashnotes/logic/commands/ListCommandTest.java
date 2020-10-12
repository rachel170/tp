package seedu.flashnotes.logic.commands;

import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashNotes(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, String.format(ListCommand.MESSAGE_SUCCESS,
                model.getFilteredFlashcardList().size()), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        assertCommandSuccess(new ListCommand(), model, String.format(ListCommand.MESSAGE_SUCCESS,
                7), expectedModel);
    }
}
