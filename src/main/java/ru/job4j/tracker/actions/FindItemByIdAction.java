package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

/**
 * Реализация процесса нахождения заявки в хранилище по идентификатору
 */
public class FindItemByIdAction implements UserAction {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     */
    public FindItemByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "FindItemById";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Find item by Id ===");
        int id = input.askInt("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            out.println(item);
        } else {
            out.println("The item with id = " + id + " was not found");
        }
        return true;
    }
}
