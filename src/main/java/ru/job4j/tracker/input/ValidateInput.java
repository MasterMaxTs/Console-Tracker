package ru.job4j.tracker.input;

import ru.job4j.tracker.output.Output;

/**
 * Реализация валидации вводимых данных
 */
public class ValidateInput implements Input {

    /**
     * Зависимость от интерфейса отображения данных
     */
    private final Output out;

    /**
     * Зависимость от интерфейса ввода данных
     */
    private final Input in;

    /**
     * Конструктор
     * @param out реализация интерфейса отображения данных
     * @param in реализация интерфейса ввода данных
     */
    public ValidateInput(Output out, Input in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public String askString(String question) {
        return in.askString(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = in.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
