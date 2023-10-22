package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

import java.util.List;

/**
 * Реализация процесса нахождения списка заявок в хранилище по ключу в названии
 */
public class FindItemByNameAction implements UserAction {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     */
    public FindItemByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "FindItemByName";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find items by key in item name ===");
        String name = input.askString("Enter the key in the item name: ");
        List<Item> item = tracker.findByKeyInName(name);
        if (item.size() > 0) {
            for (Item value : item) {
                out.println(value);
            }
        } else {
            out.println("Items with key in name \"" + name + "\" "
                    + "were not found");
        }
        return true;
    }
}
