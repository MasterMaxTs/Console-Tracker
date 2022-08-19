package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.FindItemByIdAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.stubs.StubOutput;
import ru.job4j.tracker.trackers.MemTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindItemByIdActionTest {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final LocalDateTime created = LocalDateTime.now();

    @Test
    public void whenFindItemByIdThanSuccess() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
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
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("item"));
        FindItemByIdAction findById = new FindItemByIdAction(out);
        Input input = mock(Input.class);
        findById.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by Id ===" + ls
                + "The item with id = 0 was not found" + ls));
    }
}