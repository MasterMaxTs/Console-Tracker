package ru.job4j.tracker.profiling;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.trackers.MemTracker;
import ru.job4j.tracker.trackers.Store;

/**
 * Класс используется для профилирования приложения с помощью JConsole при
 * реализации хранилища заявок в локальной памяти
 */
public class ProfilingMemTracker {

    public static void main(String[] args) throws InterruptedException {
        long start;
        long finish;
        start = System.currentTimeMillis();
        Store memTracker = new MemTracker();
        /* Add items */
        for (int i = 0; i < 999999; i++) {
            Item item = new Item("item #" + i);
            item.setId(i);
            memTracker.add(item);
        }
        /* Delete items */
        for (int i = 0; i < 499999; i++) {
            memTracker.delete(i);
        }
        finish = System.currentTimeMillis();
        System.out.printf("Application runtime: %d", finish - start);
        Thread.sleep(100000);
    }
}
