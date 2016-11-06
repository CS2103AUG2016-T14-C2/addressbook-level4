//@@author A0097627N
package guitests;

import guitests.guihandles.TaskCardHandle;

import org.junit.Test;

import seedu.savvytasker.logic.commands.UndoCommand;
import seedu.savvytasker.logic.commands.RedoCommand;
import seedu.savvytasker.logic.commands.HelpCommand;
import seedu.savvytasker.testutil.TestTask;
import seedu.savvytasker.testutil.TestUtil;

import static org.junit.Assert.assertTrue;
import static seedu.savvytasker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.savvytasker.logic.commands.RedoCommand.MESSAGE_REDO_ACKNOWLEDGEMENT;

public class RedoCommandTest extends SavvyTaskerGuiTest {

    TestTask[] expectedList = td.getTypicalTasks();
    TestTask[] currentList = td.getTypicalTasks();
    TestTask firstTaskToAdd = td.happy;
    TestTask secondTaskToAdd = td.haloween;
    TestTask pjmTaskToAdd = td.pjm;
    TestTask projectMeetingTaskToAdd = td.projectMeeting;

    @Test
    // redo one add command
    public void redoAddTest() {
        expectedList = TestUtil.addTasksToList(currentList, firstTaskToAdd);
        commandBox.runCommand(firstTaskToAdd.getAddCommand());
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    @Test
    // redo a delete command
    public void redoDeleteTest() {
        expectedList = TestUtil.removeTaskFromList(currentList, 1);
        commandBox.runCommand("delete 1");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    @Test
    // redo clear command
    public void redoClearTest() {
        commandBox.runCommand("clear");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        assertListSize(0);
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    @Test
    // redo alias command
    public void redoAliasTest() {
        expectedList = td.getTypicalTasks();
        expectedList = TestUtil.addTasksToList(expectedList, projectMeetingTaskToAdd);
        commandBox.runCommand("alias k/pjm r/Project Meeting");
        commandBox.runCommand("undo"); 
        commandBox.runCommand("redo");
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
        commandBox.runCommand(pjmTaskToAdd.getAddCommand());
        assertTrue(taskListPanel.isListMatching(expectedList));
    }

    @Test
    // redo unalias command
    public void redoUnaliasTest() {
        expectedList = TestUtil.addTasksToList(currentList, pjmTaskToAdd);
        commandBox.runCommand("alias k/pjm r/Project Meeting");
        commandBox.runCommand("unalias pjm");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
        commandBox.runCommand(pjmTaskToAdd.getAddCommand());
        assertTrue(taskListPanel.isListMatching(expectedList));
    }

    // redo two add commands
    @Test
    public void redoTwoAddTest() {
        expectedList = TestUtil.addTasksToList(currentList, firstTaskToAdd);
        expectedList = TestUtil.addTasksToList(expectedList, secondTaskToAdd);
        commandBox.runCommand(firstTaskToAdd.getAddCommand());
        commandBox.runCommand(secondTaskToAdd.getAddCommand());
        commandBox.runCommand("undo");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        commandBox.runCommand("redo");
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    // redo two delete commands
    @Test
    public void redoTwoDeleteTest() {
        expectedList = TestUtil.removeTaskFromList(currentList, 1);
        expectedList = TestUtil.removeTaskFromList(expectedList, 1);
        commandBox.runCommand("delete 1");
        commandBox.runCommand("delete 1");
        commandBox.runCommand("undo");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        commandBox.runCommand("redo");
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    // redo a delete command followed by an add command
    @Test
    public void redoDeleteAddTest() {
        expectedList = TestUtil.addTasksToList(currentList, firstTaskToAdd);
        expectedList = TestUtil.removeTaskFromList(expectedList, 1);
        commandBox.runCommand(firstTaskToAdd.getAddCommand());
        commandBox.runCommand("delete 1");
        commandBox.runCommand("undo");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        commandBox.runCommand("redo");
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    // redo an add command followed by a delete command
    @Test
    public void redoAddDeleteTest() {
        expectedList = TestUtil.removeTaskFromList(currentList, 1);
        expectedList = TestUtil.addTasksToList(expectedList, firstTaskToAdd);
        commandBox.runCommand("delete 1");
        commandBox.runCommand(firstTaskToAdd.getAddCommand());
        commandBox.runCommand("undo");
        commandBox.runCommand("undo");
        commandBox.runCommand("redo");
        commandBox.runCommand("redo");
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(MESSAGE_REDO_ACKNOWLEDGEMENT);
    }

    // invalid command
    @Test
    public void invalidTest() {
        commandBox.runCommand("redos");
        assertResultMessage("Input: redos\n" + String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE));
    }
}
//@@author