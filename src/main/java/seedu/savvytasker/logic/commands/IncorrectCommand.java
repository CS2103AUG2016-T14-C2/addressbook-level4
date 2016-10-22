package seedu.savvytasker.logic.commands;


/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {
    public final String resolvedText;
    public final String feedbackToUser;

    public IncorrectCommand(String resolvedText, String feedbackToUser){
        this.resolvedText = resolvedText;
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        indicateAttemptToExecuteIncorrectCommand();
        return new CommandResult(feedbackToUser);
    }
    
    @Override
    public boolean canUndo() {
        return false;
    }

    /**
     * Redo the "incorrect" command
     * @return true if the operation completed successfully, false otherwise
     */
    @Override
    public boolean redo() {
        // nothing required to be done
        return true;
    }

    /**
     * Undo the "incorrect" command
     * @return true if the operation completed successfully, false otherwise
     */
    @Override
    public boolean undo() {
        // nothing required to be done
        return true;
    }

}

