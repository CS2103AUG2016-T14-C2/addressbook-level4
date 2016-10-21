package seedu.savvytasker.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.savvytasker.commons.exceptions.DuplicateDataException;

import java.util.*;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 */
public class TaskList implements Iterable<Task> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        
        /**
         * Generated serial ID
         */
        private static final long serialVersionUID = 8933810750762846403L;

        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {

        /**
         * Generated serial ID
         */
        private static final long serialVersionUID = -7591982407764643511L;
    }

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private int nextId = 0;
    private boolean isNextIdInitialized = false;
    
    /**
     * Constructs empty TaskList.
     */
    public TaskList() {}
    
    /**
     * Gets the next available id for uniquely identifying a task in
     * Savvy Tasker.
     * @return The next available id;
     */
    public int getNextId() {
        if (!isNextIdInitialized) {
            int nextLowest = -1; // first id to be used is 0. Start finding with -1
            LinkedList<Integer> usedIds = new LinkedList<Integer>();
            for (Task t : internalList) {
                usedIds.add(t.getId());
                if (t.getId() > nextLowest) {
                    nextLowest = t.getId();
                }
            }
            // assumption that the number of tasks < 2^31
            // implementation will be buggy if nextId exceeds 2^31
            nextId = nextLowest;
            assert nextId < Integer.MAX_VALUE;
            isNextIdInitialized = true;
        }
        nextId++;
        return nextId;
    }

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException if the person to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    /**
     * Replaces the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean replace(ReadOnlyTask toReplace, Task replacement) throws TaskNotFoundException {
        assert toReplace != null;
        assert replacement != null;
        if (internalList.contains(toReplace)) {
            internalList.set(internalList.indexOf(toReplace), replacement);
            return true;
        }
        else {
            throw new TaskNotFoundException();
        }
    }

    public ObservableList<Task> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && this.internalList.equals( ((TaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}