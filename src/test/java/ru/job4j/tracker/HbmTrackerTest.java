package ru.job4j.tracker;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.*;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.trackers.HbmTracker;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {

    private static StandardServiceRegistry serviceRegistry;
    private HbmTracker tracker;

    @BeforeClass
    public static void initialization() {
        serviceRegistry =
                new StandardServiceRegistryBuilder().configure().build();
    }

    @AfterClass
    public static void destroy() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    @Before
    public void whenSetUp() {
        tracker = new HbmTracker(serviceRegistry);
        tracker.init();
    }

    @After
    public void wipeTable() {
        tracker.deleteAll();
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item();
        item.setName("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenAddNewItemsThenFindAll() {
        tracker.add(new Item("test1"));
        tracker.add(new Item("test2"));
        List<Item> rsl = tracker.findAll();
        assertThat(rsl.get(0).getName(), is("test1"));
        assertThat(rsl.get(1).getName(), is("test2"));
        assertThat(rsl.size(), is(2));
    }

    @Test
    public void whenReplaceItemThanTrackerHasSameUpdatedItem() {
        Item item = new Item("test");
        tracker.add(item);
        Item itemFromDb = tracker.findById(item.getId());
        int id = itemFromDb.getId();
        itemFromDb.setName("updated");
        boolean execute = tracker.replace(id, itemFromDb);
        assertTrue(execute);
        assertThat(tracker.findById(id).getName(), is("updated"));
    }

    @Test
    public void whenReplaceNonExistentItemThanTrackerExecutionFalse() {
        Item item = new Item("test1");
        tracker.add(item);
        Item itemFromDb = tracker.findById(item.getId());
        int id = itemFromDb.getId();
        itemFromDb.setName("updated");
        boolean execute = tracker.replace(id + 1, itemFromDb);
        assertFalse(execute);
        assertThat(tracker.findById(id).getName(), is("test1"));
    }

    @Test
    public void whenDeleteItemWithCorrectIdThanTrackerExecutionTrue() {
        Item item = new Item("test1");
        tracker.add(item);
        int id = item.getId();
        boolean execute = tracker.delete(id);
        assertTrue(execute);
        assertThat(tracker.findAll(), is(List.of()));
    }

    @Test
    public void whenDeleteItemWithInCorrectIdThanTrackerExecutionFalse() {
        Item item = new Item("test1");
        tracker.add(item);
        int id = item.getId();
        boolean execute = tracker.delete(id + 1);
        assertFalse(execute);
        assertThat(tracker.findAll().get(0).getName(), is("test1"));
    }

    @Test
    public void whenFindItemByIdThanTrackerHasSameItem() {
        Item item = new Item("test1");
        tracker.add(item);
        int id = item.getId();
        Item rsl = tracker.findById(id);
        assertThat(rsl.getName(), is("test1"));
    }

    @Test
    public void whenFindItemByIncorrectIdThanTrackerExecutionNull() {
        Item item = new Item("test1");
        tracker.add(item);
        int id = item.getId();
        assertNull(tracker.findById(id + 1));
    }

    @Test
    public void whenFindItemByKeyInNameThanTrackerHasListSameItemsName() {
        Item first = new Item("The first item");
        Item second = new Item("The second item");
        Item third = new Item("The first item");
        List<Item> items = List.of(first, second, third);
        items.forEach(i -> tracker.add(i));
        String search = "first";
        List<Item> rsl = tracker.findByKeyInName(search);
        assertThat(rsl.size(), is(2));
        assertThat(tracker.findByKeyInName("***"), is(List.of()));
    }
}
