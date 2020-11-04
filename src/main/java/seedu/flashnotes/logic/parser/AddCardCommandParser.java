package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;

import java.util.stream.Stream;

import seedu.flashnotes.logic.commands.AddCardCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCardCommandParser implements Parser<AddCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCardCommand parse(String args, String deckName) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER);
        String additionalMessage = "";

        assert deckName != null;

        if (deckName.equals(Model.getReservedDeckName())) {
            deckName = Model.getDefaultDeckName();
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getCountValue(PREFIX_QUESTION).get() > 1) {
            additionalMessage += "\nThere are more than 1 occurrences of q/ prefix. Only the last one is taken.";
        }

        if (argMultimap.getCountValue(PREFIX_ANSWER).get() > 1) {
            additionalMessage += "\nThere are more than 1 occurrences of a/ prefix. Only the last one is taken.";
        }

        Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());
        Tag tag = new Tag(deckName);

        Flashcard flashcard = new Flashcard(question, answer, tag);

        AddCardCommand addCardCommand = new AddCardCommand(flashcard);
        addCardCommand.setAdditionalMessage(additionalMessage);
        return addCardCommand;
    }

    @Override
    public AddCardCommand parse(String userInput) throws ParseException {
        return parse(userInput, "Default");
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
