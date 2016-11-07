package guitests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandBoxTest extends SavvyTaskerGuiTest {

    @Test
    public void commandBox_commandSucceeds_textCleared() {
        commandBox.runCommand(td.haloween.getAddCommand());
        assertEquals(commandBox.getCommandInput(), "");
    }

    @Test
    public void commandBox_commandFails_textClears(){
        commandBox.runCommand("invalid command");
        assertEquals(commandBox.getCommandInput(), "");
        //TODO: confirm the text box color turns to red
    }

}
