package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.logic.commands.models.ModifyCommandModel;
import seedu.address.model.person.TaskList.TaskNotFoundException;
import seedu.address.model.person.*;

/**
 * Adds a person to the address book.
 */
public class ModifyCommand extends Command {

    public static final String COMMAND_WORD = "modify";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Madifies a task in Savvy Tasker. "
            + "Parameters: INDEX [t/TASK_NAME] [s/START_DATE] [st/START_TIME] [e/END_DATE] [et/END_TIME] [l/LOCATION] [p/PRIORITY_LEVEL]"
            + "[r/RECURRING_TYPE] [n/NUMBER_OF_RECURRENCE] [c/CATEGORY] [d/DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD
            + " 1 t/Project Meeting s/05-10-2016 st/14:00 et/18:00 r/daily n/2 c/CS2103 d/Discuss about roles and milestones";

    public static final String MESSAGE_SUCCESS = "Task modified: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";

    private final ModifyCommandModel commandModel;

    /**
     * Creates an add command.
     */
    public ModifyCommand(ModifyCommandModel commandModel) {
        this.commandModel = commandModel;
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        assert commandModel != null;

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < commandModel.getIndex()) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToModify = lastShownList.get(commandModel.getIndex() - 1);
        Task replacement = new Task(taskToModify, commandModel);

        try {
            model.modifyTask(taskToModify, replacement);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        
        return new CommandResult(String.format(MESSAGE_SUCCESS, replacement));
    }
    
    @Override
    protected boolean canUndo() {
        return true;
    }

    /**
     * Redo the add command
     * @return true if the operation completed successfully, false otherwise
     */
    @Override
    protected boolean redo() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * Undo the add command
     * @return true if the operation completed successfully, false otherwise
     */
    @Override
    protected boolean undo() {
        // TODO Auto-generated method stub
        return false;
    }

}
