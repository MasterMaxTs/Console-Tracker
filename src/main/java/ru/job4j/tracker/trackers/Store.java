package ru.job4j.tracker.trackers;

import ru.job4j.tracker.models.Item;

import java.util.List;

/**
 * Хранилище заявок
 */
public interface Store extends AutoCloseable {

    /**
     * Производит инициализацию данных при работе с хранилищем
     */
    void init();

    /**
     * Добавляет заявку в хранилище
     * @param item заявка на входе
     * @return заявку с проинициализированным идентификатором
     */
    Item add(Item item);

    /**
     * Замечает заявку в хранилище по идентификатору
     * @param id идентификатор заявки на входе
     * @param item заявка на входе
     * @return результат операции в виде boolean
     */
    boolean replace(int id, Item item);

    /**
     * Удаляет заявку из хранилища по идентификатору
     * @param id идентификатор заявки на входе
     * @return результат операции в виде boolean
     */
    boolean delete(int id);

    /**
     * Находит все заявки из хранилища
     * @return список заявок
     */
    List<Item> findAll();

    /**
     * Находит список заявок из хранилища, содержащих в названии
     * искомое слово
     * @param key искомое слово в названии заявки на входе
     * @return список заявок
     */
    List<Item> findByKeyInName(String key);

    /**
     * Находит заявку из хранилища по идентификатору
     * @param id идентификатор заявки
     * @return заявку или null
     */
    Item findById(int id);
}
