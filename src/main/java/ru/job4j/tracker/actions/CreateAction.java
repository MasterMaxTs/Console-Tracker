package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

/**
 * Реализация процесса создания заявки
 */
public class CreateAction implements UserAction {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     */
    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new Item ====");
        String name = input.askString("Enter name: ");
        String description = input.askString("Enter description: ");
        Item item = new Item(name, description);
        tracker.add(item);
        return true;
    }
}
