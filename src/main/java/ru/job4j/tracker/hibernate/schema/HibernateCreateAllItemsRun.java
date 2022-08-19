package ru.job4j.tracker.hibernate.schema;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.models.Item;

import java.util.ArrayList;
import java.util.List;

public class HibernateCreateAllItemsRun {

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>(List.of(
                new Item(
                        "first",
                        "it's description for first item"),
                new Item(
                        "second",
                        "it's description for second item"),
                new Item(
                        "third",
                        "it's description for third item")
        ));
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();

        try {
            SessionFactory sessionFactory =
                    new MetadataSources(registry)
                            .buildMetadata()
                            .buildSessionFactory();
            createAll(sessionFactory, items);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void createAll(SessionFactory sf, List<Item> items) {
        items.forEach(item -> create(sf, item));
    }

    private static void create(SessionFactory sf, Item item) {
        Session session = sf.openSession();
        session.getTransaction();
        session.save(item);
        session.close();
    }
}
