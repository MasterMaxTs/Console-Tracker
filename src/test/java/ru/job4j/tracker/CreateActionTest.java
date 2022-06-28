package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateActionTest {

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        CreateAction create = new CreateAction(output);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("itemName");
        create.execute(input, tracker);
        assertThat(tracker.findAll().get(0).getName(), is("itemName"));
        assertThat(tracker.findAll().size(), is(1));
    }
}