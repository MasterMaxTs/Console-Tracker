package ru.job4j.tracker.output;

/**
 * Реализация отображения данных в консоль приложения
 */
public class ConsoleOutput implements Output {

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
}
