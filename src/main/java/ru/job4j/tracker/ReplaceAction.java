package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "ReplaceItem";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Replace a current Item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askString("Enter name: ");
        Item item = new Item(name);
        if (memTracker.replace(id, item)) {
            out.println("Replace success!");
        } else {
            out.println("Error!");
        }
        return true;
    }
}
