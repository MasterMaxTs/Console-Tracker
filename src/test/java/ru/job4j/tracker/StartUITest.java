package ru.job4j.tracker;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Created Item", "1"}
        );
        Output output = new ConsoleOutput();
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new Exit(output));
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Created Item"));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
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
        MemTracker memTracker = new MemTracker();
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
        MemTracker memTracker = new MemTracker();
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
    public void whenFindByNameItem() {
        MemTracker memTracker = new MemTracker();
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
                        + "=== Find items by name ===" + System.lineSeparator()
                        + memTracker.findAll().get(1) + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. FindItemByName" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "Exit programme success!" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByIdItem() {
        MemTracker memTracker = new MemTracker();
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
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"7", "0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit(out));
        new StartUI(out).init(in, memTracker, actions);
        assertThat(out.toString(), is(
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