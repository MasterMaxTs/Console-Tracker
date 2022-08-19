package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();

    private SessionFactory sf;

    @Override
    public void init() {
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        boolean rsl = session.createQuery(
                        "update Item set name = :newName, description = "
                                + ":newDescription, created = :newCreated"
                                + " where id = :fId"
                ).setParameter("newName", item.getName())
                .setParameter("newDescription", item.getDescription())
                .setParameter("newCreated", item.getCreated())
                .setParameter("fId", id)
                .executeUpdate() > 0;
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        boolean rsl = session.createQuery(
                        "delete from Item where id = :fId"
                ).setParameter("fId", id)
                .executeUpdate() > 0;
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> rsl = session.createQuery("from Item", Item.class).list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> rsl = session.createQuery(
                        "from Item where name = :fName", Item.class
                )
                .setParameter("fName", key)
                .list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item rsl = session.createQuery("from Item where id = :fId", Item.class)
                        .setParameter("fId", id)
                        .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
