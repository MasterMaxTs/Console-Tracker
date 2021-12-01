package ru.job4j.tracker;

import org.junit.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {

    private static Connection connection;
    private SqlTracker tracker;
    private Item item1;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in =
                     SqlTrackerTest.class.getClassLoader().getResourceAsStream(
                             "test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Before
    public void insertFirstItemIntoTable() {
        tracker = new SqlTracker(connection);
        item1 = new Item("item1");
        tracker.add(item1);
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "DELETE FROM items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        assertThat(tracker.findById(item1.getId()), is(item1));
    }

    @Test
    public void whenSaveItemsAndFindAll() {
        Item item2 = new Item("item2");
        tracker.add(item2);
        assertThat(tracker.findAll(), is(List.of(item1, item2)));
    }

    @Test
    public void whenSaveItemsAndDeleteOne() {
        Item item2 = new Item("item2");
        tracker.add(item2);
        boolean rsl = tracker.delete(item1.getId());
        assertTrue(rsl);
        assertEquals(1, tracker.findAll().size());
        assertEquals("item2", tracker.findAll().get(0).getName());
    }

    @Test
    public void whenDeleteNonExistentItemThanFalse() {
        int lastId = item1.getId();
        assertFalse(tracker.delete(lastId + 1));
    }

    @Test
    public void whenFindItemWithNonExistentIdThanNull() {
        int lastId = item1.getId();
        assertNull(tracker.findById(lastId + 1));
    }

    @Test
    public void whenSaveItemsAndFindByName() {
        Item item2 = new Item("item2");
        Item item3 = new Item("item1");
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("item1"), is(List.of(item3, item1)));
    }

    @Test
    public void whenFindItemWithNonExistentNameThanRslIsEmpty() {
        assertThat(tracker.findByName("otherItem"), is(List.of()));
    }

    @Test
    public void whenSaveItemsAndReplaceOne() {
        Item otherItem = new Item("replaced");
        otherItem.setId(item1.getId());
        boolean rsl = tracker.replace(item1.getId(), otherItem);
        assertTrue(rsl);
        assertThat(tracker.findAll(), is(List.of(otherItem)));
    }

    @Test
    public void whenSaveItemsAndReplaceNonExistentItemThanFalse() {
        int lastId = item1.getId();
        assertFalse(tracker.replace(lastId + 1, new Item("replaced")));
    }
}