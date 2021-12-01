package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {
    private final Output out;

    public ShowAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "ShowAllItems";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Show all items ===");
        List<Item> items = memTracker.findAll();
        for (Item value : items) {
            out.println(value);
        }
        return true;
    }
}
