package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

/**
 * Реализация процесса замещения заявки в хранилище по идентификатору
 */
public class ReplaceAction implements UserAction {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     */
    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "ReplaceItem";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Replace a current Item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askString("Enter name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            out.println("Replace success!");
        } else {
            out.println("Error!");
        }
        return true;
    }
}
