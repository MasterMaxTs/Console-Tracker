package ru.job4j.tracker.stubs;

import ru.job4j.tracker.input.Input;

/**
 * Реализация ввода данных из памяти в виде заглушки
 */
public class StubInput implements Input {

    /**
     * Набор тестовых фраз в виде строкового массива
     */
    private String[] answers;

    /**
     * Указатель индекса массива
     */
    private int position = 0;

    /**
     * Конструктор
     * @param answers строковый массив тестовых фраз
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String askString(String question) {
        return answers[position++];
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askString(question));
    }
}
