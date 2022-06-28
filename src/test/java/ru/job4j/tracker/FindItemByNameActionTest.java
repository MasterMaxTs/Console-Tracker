package ru.job4j.tracker;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindItemByNameActionTest {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final LocalDateTime created = LocalDateTime.now();

    @Test
    public void whenFindItemByNameThanSuccess() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("firstItem"));
        tracker.add(new Item("secondItem"));
        tracker.add(new Item("firstItem"));
        FindItemByNameAction findByName = new FindItemByNameAction(output);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("firstItem");
        findByName.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(output.toString(), is("=== Find items by name ===" + ls
                        + "id: 1, name: firstItem, created: " + formatter.format(created) + ls
                        + "id: 3, name: firstItem, created: " + formatter.format(created) + ls));
    }

    @Test
    public void whenFindItemByNameThanError() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("itemName"));
        FindItemByNameAction findByName = new FindItemByNameAction(output);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("found");
        findByName.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(output.toString(), is("=== Find items by name ===" + ls
                + "Items with name \"found\" were not found" + ls));
    }
}