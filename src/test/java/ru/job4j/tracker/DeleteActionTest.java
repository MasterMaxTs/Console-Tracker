package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.DeleteAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.stubs.StubOutput;
import ru.job4j.tracker.trackers.MemTracker;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Test
    public void whenDeleteItemSuccess() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("firstItem"));
        tracker.add(new Item("secondItem"));
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(2);
        del.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete Item ===" + ls + "Item # 2 "
                + "has been deleted successfully!" + ls));
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void whenDeleteItemWithError() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("firstItem"));
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(3);
        del.execute(input, tracker);
        String ls = System.lineSeparator();
        assertThat(out.toString(),
                is("=== Delete Item ===" + ls + "Error has been occurred!" + ls));
        assertThat(tracker.findAll().size(), is(1));
    }
}