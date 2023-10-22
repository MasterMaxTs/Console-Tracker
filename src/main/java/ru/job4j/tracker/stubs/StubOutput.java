package ru.job4j.tracker.stubs;

import ru.job4j.tracker.output.Output;

/**
 * Реализация отображения данных из памяти в виде заглушки
 */
public class StubOutput implements Output {

    /**
     * Зависимость от объекта StringBuilder
     */
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void println(Object obj) {
        if (obj != null) {
            buffer.append(obj);
        } else {
            buffer.append("null");
        }
        buffer.append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
