package ru.job4j.tracker.input;

import java.util.Scanner;

/**
 * Реализация ввода данных из консоли приложения
 */
public class ConsoleInput implements Input {

    /**
     * Зависимость от объекта Scanner
     */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askString(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askString(question));
    }
}
