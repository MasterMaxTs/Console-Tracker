package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.Store;

/**
 * Реализация процесса удаления заявки
 */
public class DeleteAction implements UserAction {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     */
    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete Item ===");
        int id = input.askInt("Enter id: ");
        if (tracker.delete(id)) {
            out.println("Item # " + id + " has been deleted successfully!");
        } else {
            out.println("Error has been occurred!");
        }
        return true;
    }
}
