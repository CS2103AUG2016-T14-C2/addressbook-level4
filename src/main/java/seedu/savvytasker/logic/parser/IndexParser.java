package seedu.savvytasker.logic.parser;

import java.util.Arrays;

/**
 * A class for parsing a single index or multiple indices. An index is 
 * a positive integer that may be used by commands to indicate which task
 * they act on.
 */
public class IndexParser {    
    /**
     * Parses a single index.
     * 
     * @param indexText the text to parse
     * @return the resulting index
     * @throws ParseException if there are more than one integer in the text, or if 
     *      the integer given is not positive
     */
    public int parseSingle(String indexText) throws ParseException {
        boolean parseError = false;
        
        int index = 0;
        try {
            indexText = indexText.trim();
            index = Integer.parseInt(indexText);
            
            if (index <= 0)
                parseError = true;
        } catch (NumberFormatException ex) {
            parseError = true;
        }
        
        if (parseError)
            throw new ParseException(indexText, "Must be a positive whole number.");
            
        return index;
    }
    
    /**
     * Parses multiple indices, delimited by space.
     * 
     * @param indicesText the text to parse
     * @return an array of the resulting indices
     * @throws ParseException if any of the indices is not a positive integer
     */
    public int[] parseMultiple(String indicesText) throws ParseException {
        boolean parseError = false;
        
        int[] indices = null;
        try {
            indices = Arrays
                .stream(indicesText.trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
            
            for(int index : indices) {
                if (index <= 0) {
                    parseError = true;
                    break;
                }
            }
        } catch (NumberFormatException ex) {
            parseError = true;
        }
        
        if (parseError)
            throw new ParseException(indicesText, "Must be positive whole numbers.");
            
        return indices;
    }
}
