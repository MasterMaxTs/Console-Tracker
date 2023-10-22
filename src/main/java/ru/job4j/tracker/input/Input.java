package ru.job4j.tracker.input;

/**
 * Интерфейс ввода данных
 */
public interface Input {

    /**
     * Получает данные от пользователя в виде строки
     * @param question Запрос пользователю в виде строки на ввод данных
     * @return данные от пользователя в виде строки
     */
    String askString(String question);

    /**
     * Получает данные от пользователя в виде числа
     * @param question Запрос пользователю в виде строки на ввод данных
     * @return данные от пользователя в виде числа
     */
    int askInt(String question);
}
