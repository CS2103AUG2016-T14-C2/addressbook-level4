package seedu.savvytasker.logic.commands;

import seedu.savvytasker.logic.commands.models.AddCommandModel;
import seedu.savvytasker.model.person.*;
import seedu.savvytasker.model.person.TaskList.DuplicateTaskException;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to Savvy Tasker. "
            + "Parameters: TASK_NAME [s/START_DATE] [st/START_TIME] [e/END_DATE] [et/END_TIME] [l/LOCATION] [p/PRIORITY_LEVEL]"
            + "[r/RECURRING_TYPE] [n/NUMBER_OF_RECURRENCE] [c/CATEGORY] [d/DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD
            + " Project Meeting s/05-10-2016 st/14:00 et/18:00 r/daily n/2 c/CS2103 d/Discuss about roles and milestones";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";

    private final Task toAdd;

    /**
     * Creates an add command.
     */
    public AddCommand(AddCommandModel commandModel) {
        this.toAdd = new Task(commandModel.getTaskName(),
                commandModel.getStartDateTime(), commandModel.getEndDateTime(),
                commandModel.getLocation(), commandModel.getPriority(),
                commandModel.getRecurringType(), commandModel.getNumberOfRecurrence(),
                commandModel.getCategory(), commandModel.getDescription()
        );
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }

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
