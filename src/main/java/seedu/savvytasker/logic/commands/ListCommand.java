package seedu.savvytasker.logic.commands;

import seedu.savvytasker.logic.commands.models.ListCommandModel;
import seedu.savvytasker.model.task.ListType;

/**
 * Lists all tasks in the savvy tasker to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the tasks in the task list.\n"
            + "Parameters: [t/LIST_TYPE]\n"
            + "Example: " + COMMAND_WORD + " t/Archived";
    
    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    private final ListCommandModel commandModel;
    
    /**
     * Creates the List command to list the specified tasks
     * @author A0139915W
     * @param commandModel Arguments for the List command, must not be null
     */
    public ListCommand(ListCommandModel commandModel) {
        assert commandModel != null;
        this.commandModel = commandModel;
    }

    @Override
    public CommandResult execute() {
        ListType listType = commandModel.getListType();
        if (listType == null) {
            // use default, sort by due date
            listType = ListType.DueDate;
        }
        switch (listType)
        {
        case DueDate:
            model.updateFilteredListToShowActiveSortedByDueDate();
            break;
        case PriorityLevel:
            model.updateFilteredListToShowActiveSortedByPriorityLevel();
            break;
        case Archived:
            model.updateFilteredListToShowArchived();
            break;
        default:
            assert false; // should not reach here
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    @Override
    public boolean canUndo() {
        return false;
    }

    /**
     * Redo the list command
     * @return true if the operation completed successfully, false otherwise
     */
    @Override
    public boolean redo() {
        // nothing required to be done
        return true;
    }

    
    /**
     * Undo the list command
     * @return true if the operation completed successfully, false otherwise
     */
    @Override
    public boolean undo() {
        // nothing required to be done
        return true;
    }
}
