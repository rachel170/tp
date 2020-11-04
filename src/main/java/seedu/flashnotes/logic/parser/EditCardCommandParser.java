package seedu.flashnotes.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.EditCardCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCardCommandParser implements Parser<EditCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCardCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT
                    + "\n" + pe.getMessage(), EditCardCommand.MESSAGE_USAGE), pe);
        }

        EditCardCommand.EditFlashcardDescriptor editFlashcardDescriptor = new EditCardCommand.EditFlashcardDescriptor();
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            editFlashcardDescriptor.setQuestion(ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            editFlashcardDescriptor.setAnswer(ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editFlashcardDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (!editFlashcardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCardCommand.MESSAGE_NOT_EDITED);
        }

        ParserUtil.parseDeckName(editFlashcardDescriptor.getTag().toString());

        return new EditCardCommand(index, editFlashcardDescriptor);
    }

}
