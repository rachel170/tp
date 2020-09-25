package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.WHO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Question;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_QUESTION = "wh@";
    private static final String INVALID_ANSWER = "a";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_QUESTION = WHO.getQuestion().toString();
    private static final String VALID_ANSWER = WHO.getAnswer().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = WHO.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(WHO);
        assertEquals(WHO, person.toModelType());
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(INVALID_QUESTION, VALID_ANSWER, VALID_TAGS);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(null, VALID_ANSWER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(VALID_QUESTION, INVALID_ANSWER, VALID_TAGS);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard person = new JsonAdaptedFlashcard(VALID_QUESTION, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashcard person =
                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
