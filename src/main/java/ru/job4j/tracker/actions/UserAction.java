package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.trackers.Store;

/**
 * Действия пользователя
 */
public interface UserAction {

    /**
     * Выводит название действия
     * @return название действия
     */
    String name();

    /**
     * Запускает выполнение действия над заявкой
     * @param input Интерфейс вводимых данных
     * @param store Интерфейс в виде хранилища заявок
     * @return результат выполнения в виде boolean
     */
    boolean execute(Input input, Store store);
}
