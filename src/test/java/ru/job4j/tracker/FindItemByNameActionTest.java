package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.actions.FindItemByNameAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.stubs.StubOutput;
import ru.job4j.tracker.trackers.MemTracker;
import ru.job4j.tracker.trackers.Store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindItemByNameActionTest {

    private Store tracker;

    private Output out;

    private DateTimeFormatter formatter;

    private LocalDateTime created;

    @Before
    public void whenSetUp() {
        out = new StubOutput();
        tracker = new MemTracker();
        tracker.init();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        created = LocalDateTime.now();
    }

    @Test
    public void whenFindItemByKeyInNameThanSuccess() {
        tracker.init();
        tracker.add(new Item("first Item"));
        tracker.add(new Item("second Item"));
        tracker.add(new Item("first Item"));
        FindItemByNameAction findByName = new FindItemByNameAction(out);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("first");
        findByName.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by key in item name ===" + ls
                        + "id: 1, name: first Item, created: " + formatter.format(created) + ls
                        + "id: 3, name: first Item, created: " + formatter.format(created) + ls));
    }

    @Test
    public void whenFindItemByNameThanError() {
        tracker.add(new Item("itemName"));
        FindItemByNameAction findByName = new FindItemByNameAction(out);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("****");
        findByName.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by key in item name ===" + ls
                + "Items with key in name \"****\" were not found" + ls));
    }
}