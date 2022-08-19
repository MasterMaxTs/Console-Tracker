package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Delete Item ===");
        int id = input.askInt("Enter id: ");
        if (memTracker.delete(id)) {
            out.println("Item # " + id + " has been deleted successfully!");
        } else {
            out.println("Error has been occurred!");
        }
        return true;
    }
}
