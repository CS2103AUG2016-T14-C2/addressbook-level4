package seedu.savvytasker.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.savvytasker.commons.core.Messages;
import seedu.savvytasker.logic.commands.MarkCommand;

public class MarkCommandParser implements CommandParser<MarkCommand> {
    private static final String HEADER = "mark";
    private static final String READABLE_FORMAT = HEADER+" INDEX [MORE_INDEX]";
    
    private static final String REGEX_REF_INDICES = "Indices";
    
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            HEADER+"\\s+(?<"+REGEX_REF_INDICES+">[^/]+)", Pattern.CASE_INSENSITIVE);
    
    private static final IndexParser INDEX_PARSER = new IndexParser();
    
    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getRequiredFormat() {
        return READABLE_FORMAT;
    }

    @Override
    public MarkCommand parse(String commandText) throws ParseException {
        Matcher matcher = REGEX_PATTERN.matcher(commandText);
        if (matcher.matches()) {
            parseIndices(matcher.group(REGEX_REF_INDICES));
            
            // TODO: Integrate with MarkCommand properly
            return new MarkCommand();
        }

        throw new ParseException(commandText, String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, getRequiredFormat()));
    }
    
    private int[] parseIndices(String indicesText) throws ParseException {
        try {
            return INDEX_PARSER.parseMultiple(indicesText);
        } catch (ParseException ex) {
            throw new ParseException(indicesText, "INDEX [MORE_INDEX]: " + ex.getFailureDetails());
        }
    }

}
