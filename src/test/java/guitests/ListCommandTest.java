package guitests;

import org.junit.Test;

import seedu.savvytasker.logic.commands.HelpCommand;
import seedu.savvytasker.testutil.TestTask;

import static org.junit.Assert.assertTrue;
import static seedu.savvytasker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

//@@author A0139915W
public class ListCommandTest extends SavvyTaskerGuiTest {

    @Test
    public void list_nonEmptyList() {
        assertListResult("list", td.earliestDue, td.nearerDue, td.notSoNearerDue, td.furthestDue,
                td.highPriority, td.medPriority, td.lowPriority); //multiple results

        //list after deleting one result
        commandBox.runCommand("delete 1");
        assertListResult("list", td.nearerDue, td.notSoNearerDue, td.furthestDue,
                td.highPriority, td.medPriority, td.lowPriority);
    }
    
    @Test
    public void list_nonEmptyList_byInvalidSwitch() {
        commandBox.runCommand("list t/badswitch");
        assertResultMessage("LIST_TYPE: Unknown type \'badswitch\'");
    }
    
    @Test
    public void list_nonEmptyList_byDueDate() {
        // covered by list_nonEmptyList()
    }
    
    @Test
    public void list_nonEmptyList_byPriority() {
        assertListResult("list t/PriorityLevel", td.highPriority, td.medPriority, 
                td.furthestDue, td.nearerDue, td.notSoNearerDue, td.earliestDue, td.lowPriority); //multiple results
    }
    
    @Test
    public void list_nonEmptyList_byArchived() {
        assertListResult("list t/Archived", td.longDue); // one matching result only
    }

    @Test
    public void list_emptyList(){
        commandBox.runCommand("clear");
        assertListResult("list"); //no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("listmytasks");
        assertResultMessage(String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE));
    }

    private void assertListResult(String command, TestTask... expectedHits ) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " tasks listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}
//@@author
