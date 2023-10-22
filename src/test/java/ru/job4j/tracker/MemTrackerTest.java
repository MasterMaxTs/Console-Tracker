package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.trackers.MemTracker;
import ru.job4j.tracker.trackers.Store;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class MemTrackerTest {

    private Store memTracker;

    @Before
    public void whenSetUp() {
        memTracker = new MemTracker();
        memTracker.init();
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplace() {
        Item bug = new Item("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Item bug = new Item("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenFindItemsByKeyInName() {
        Item item1 = new Item("The first item 1");
        Item item2 = new Item("The second item 2");
        Item item3 = new Item("The first item 3");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        assertThat(List.of(item1, item3), is(memTracker.findByKeyInName("first")));
        assertThat(List.of(), is(memTracker.findByKeyInName("third")));
    }
}