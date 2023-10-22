package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.stubs.StubInput;
import ru.job4j.tracker.stubs.StubOutput;
import ru.job4j.tracker.trackers.MemTracker;
import ru.job4j.tracker.trackers.Store;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    private Store memTracker;

    @Before
    public void whenSetUp() {
        memTracker = new MemTracker();
        memTracker.init();
    }

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Created Item", "1"}
        );
        Output output = new ConsoleOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new Exit(output));
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Created Item"));
    }

    @Test
    public void whenReplaceItem() {
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        Output output = new ConsoleOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new Exit(output));
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        Output output = new ConsoleOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new Exit(output));
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenFindAllItem() {
        Item[] items = {memTracker.add(new Item("Item1")),
                memTracker.add(new Item("Item2")),
                memTracker.add(new Item("Item3")),
        };
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        Output output = new StubOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAllAction(output));
        actions.add(new Exit(output));
        String[] expected = {"Item1", "Item2", "Item3"};
        new StartUI(output).init(in, memTracker, actions);
        for (int i = 0; i < expected.length; i++) {
            assertThat(memTracker.findAll().get(i).getName(), is(expected[i]));
        }
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. ShowAllItems" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Show all items ===" + System.lineSeparator()
                        + items[0] + System.lineSeparator()
                        + items[1] + System.lineSeparator()
                        + items[2] + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. ShowAllItems" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "Exit programme success!" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByKeyInNameItem() {
        Item[] items = {memTracker.add(new Item("Item")),
                memTracker.add(new Item("Found Item")),
        };
        Input in = new StubInput(
                new String[] {"0", "Found Item", "1"}
        );
        Output output = new StubOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindItemByNameAction(output));
        actions.add(new Exit(output));
        String expected = "Found Item";
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(1).getName(), is(expected));
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. FindItemByName" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find items by key in item name ===" + System.lineSeparator()
                        + memTracker.findAll().get(1) + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. FindItemByName" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "Exit programme success!" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByIdItem() {
        Item[] items = {memTracker.add(new Item("Item1")),
                memTracker.add(new Item("Item2")),
                memTracker.add(new Item("Item3")),
        };
        Input in = new StubInput(
                new String[] {"0", String.valueOf(items[1].getId()), "1"}
        );
        Output output = new StubOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindItemByIdAction(output));
        actions.add(new Exit(output));
        String expected = "Item2";
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(1).getName(), is(expected));
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. FindItemById" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find item by Id ===" + System.lineSeparator()
                        + memTracker.findAll().get(1) + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. FindItemById" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "Exit programme success!" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[] {"7", "0"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit(output));
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is(
                String.format(
                        "Menu.%n"
                                + "0. Exit%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n"
                                + "0. Exit%n"
                                + "Exit programme success!%n"
                )
        ));
    }
}