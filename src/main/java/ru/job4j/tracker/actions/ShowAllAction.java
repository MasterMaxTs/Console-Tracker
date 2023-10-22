package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

import java.util.List;

/**
 * Реализация процесса Вывода всех заявок из хранилища
 */
public class ShowAllAction implements UserAction {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     */
    public ShowAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "ShowAllItems";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Show all items ===");
        List<Item> items = tracker.findAll();
        for (Item value : items) {
            out.println(value);
        }
        return true;
    }
}
