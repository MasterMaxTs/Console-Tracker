package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {

    @Test
    public void whenReplaceItemSuccess() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askString(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(),
                is("=== Replace a current Item ===" + ln + "Replace success!" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenReplaceItemWithError() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String itemName = "Replaced item";
        tracker.add(new Item(itemName));
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askString(any(String.class))).thenReturn("");
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(),
                is("=== Replace a current Item ===" + ln + "Error!" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(itemName));
    }
}