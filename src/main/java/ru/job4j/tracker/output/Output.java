package ru.job4j.tracker.output;

/**
 * Интерфейс отображения данных
 */
public interface Output {

    /**
     * Выводит текстовое представление переданного объекта
     * @param obj объект на входе
     */
    void println(Object obj);
}
