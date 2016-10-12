package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.SavvyTasker;
import seedu.address.model.person.*;
import seedu.address.model.person.TaskList.DuplicateTaskException;

/**
 *
 */
public class TypicalTestTasks {

    public static TestTask hello, meeting, assignment, tutorial, dinner, happy, haloween;

    public TypicalTestTasks() {
        try {
            hello =  new TaskBuilder().withTaskName("Hello Task").build();
            meeting =  new TaskBuilder().withTaskName("Meeting Task").build();
            assignment =  new TaskBuilder().withTaskName("Assignment Task").build();
            tutorial =  new TaskBuilder().withTaskName("Tutorial Task").build();
            dinner =  new TaskBuilder().withTaskName("Dinner Task").build();
            
            //Manually added
            happy = new TaskBuilder().withTaskName("Happy Task").build();
            haloween = new TaskBuilder().withTaskName("Haloween Task").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadSavvyTaskerWithSampleData(SavvyTasker st) {

        try {
            st.addTask(new Task(hello));
            st.addTask(new Task(meeting));
            st.addTask(new Task(assignment));
            st.addTask(new Task(tutorial));
            st.addTask(new Task(dinner));
        } catch (DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{hello, meeting, assignment, tutorial, dinner};
    }

    public SavvyTasker getTypicalSavvyTasker(){
        SavvyTasker st = new SavvyTasker();
        loadSavvyTaskerWithSampleData(st);
        return st;
    }
}