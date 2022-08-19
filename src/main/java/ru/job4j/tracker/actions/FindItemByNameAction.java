package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

import java.util.List;

public class FindItemByNameAction implements UserAction {
    private final Output out;

    public FindItemByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "FindItemByName";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        out.println("=== Find items by name ===");
        String name = input.askString("Enter name item: ");
        List<Item> item = memTracker.findByName(name);
        if (item.size() > 0) {
            for (Item value : item) {
                out.println(value);
            }
        } else {
            out.println("Items with name \"" + name + "\" were not found");
        }
        return true;
    }
}
