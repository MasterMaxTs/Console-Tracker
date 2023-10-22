package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.actions.FindItemByIdAction;
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

public class FindItemByIdActionTest {

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
    public void whenFindItemByIdThanSuccess() {
        tracker.add(new Item("firstItem"));
        tracker.add(new Item("secondItem"));
        FindItemByIdAction findById = new FindItemByIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(2);
        findById.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by Id ===" + ls
        + "id: 2, name: secondItem, created: " + formatter.format(created) + ls));
        assertThat(tracker.findAll().get(1).getName(), is("secondItem"));
    }

    @Test
    public void whenFindItemByIdThanError() {
        tracker.add(new Item("item"));
        FindItemByIdAction findById = new FindItemByIdAction(out);
        Input input = mock(Input.class);
        findById.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by Id ===" + ls
                + "The item with id = 0 was not found" + ls));
    }
}