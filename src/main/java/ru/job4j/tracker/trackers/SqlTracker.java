package ru.job4j.tracker.trackers;

import ru.job4j.tracker.models.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Реализация доступа к хранилищу заявок в БД с помощью Jdbc
 */
public class SqlTracker implements Store {

    /**
     * Зависимость от объекта Connection
     */
    private Connection cn;

    /**
     * Конструктор
     * @param cn объект Connection
     */
    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    /**
     * Создает заявку по выборке из БД
     * @param rs объект ResultSet
     * @return объект в виде заявки
     */
    private Item getItemFromDb(ResultSet rs) throws SQLException {
        return new Item(
                rs.getInt(1),
                rs.getString(2),
                rs.getTimestamp(3).toLocalDateTime()
        );
    }

    @Override
    public void init() {
        if (cn == null) {
            try (InputStream in =
                         SqlTracker.class.getClassLoader().getResourceAsStream(
                                 "app.properties")) {
                Properties config = new Properties();
                config.load(in);
                Class.forName(config.getProperty("driver-class-name"));
                cn = DriverManager.getConnection(
                        config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password")
                );
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO items (name, created) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.execute();
            try (ResultSet generatedKey = ps.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    item.setId(generatedKey.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        try (PreparedStatement ps = cn.prepareStatement(
                "UPDATE items SET name = ?, created = ? WHERE id = ?"
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement ps = cn.prepareStatement(
                "DELETE FROM items WHERE id = ?"
        )) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> listItems = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM items ORDER BY id"
        )) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listItems.add(getItemFromDb(rs));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listItems;
    }

    @Override
    public List<Item> findByKeyInName(String key) {
        List<Item> copyItem = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM items WHERE name like ? ORDER BY id DESC"
        )) {
            ps.setString(1, "%" + key + "%");
            try (ResultSet rs  = ps.executeQuery()) {
                while (rs.next()) {
                    copyItem.add(getItemFromDb(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return copyItem;
    }

    @Override
    public Item findById(int id) {
        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM items WHERE id = ?"
        )) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getItemFromDb(rs);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
