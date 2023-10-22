package ru.job4j.tracker.trackers;

import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация доступа к локальному хранилищу заявок
 */
public class MemTracker implements Store {

    /**
     * Хранилище заявок в виде динамического списка
     */
    private List<Item> items;

    /**
     * Указатель индекса списка
     */
    private int ids = 1;

    @Override
    public void init() {
        items = new ArrayList<>();
    }

    @Override
    public void close() {

    }

    @Override
    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public List<Item> findByKeyInName(String key) {
        List<Item> copyItem = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().contains(key)) {
                copyItem.add(item);
            }
        }
        return copyItem;
    }

    /**
     * Находит порядковый номер заявки в хранилище
     * @param id идентификатор заявки на входе
     * @return порядковый номер заявки в хранилище, если заявка найдена,
     * иначе -1
     */
    private int indexOf(int id) {
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            items.set(index, item);
            items.get(index).setId(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
            return true;
        }
        return false;
    }
}
