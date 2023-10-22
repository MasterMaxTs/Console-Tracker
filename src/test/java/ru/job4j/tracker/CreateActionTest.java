package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.CreateAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.stubs.StubOutput;
import ru.job4j.tracker.trackers.MemTracker;
import ru.job4j.tracker.trackers.Store;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateActionTest {

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Store memTracker = new MemTracker();
        memTracker.init();
        CreateAction create = new CreateAction(output);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("itemName");
        create.execute(input, memTracker);
        assertThat(memTracker.findAll().get(0).getName(), is("itemName"));
        assertThat(memTracker.findAll().size(), is(1));
    }
}