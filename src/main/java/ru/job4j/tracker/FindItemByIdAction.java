package ru.job4j.tracker;

public class FindItemByIdAction implements UserAction {
    private final Output out;

    public FindItemByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "FindItemById";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Find item by Id ===");
        int id = input.askInt("Enter id: ");
        Item item = memTracker.findById(id);
        if (item != null) {
            out.println(item);
        } else {
            out.println("The item with id = " + id + " was not found");
        }
        return true;
    }
}
