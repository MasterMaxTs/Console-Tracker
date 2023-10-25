package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Item;
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

    private static String ls;
    private Output output;
    private Store memTracker;
    private StartUI startUI;

    @Before
    public void whenSetUp() {
        ls = System.lineSeparator();
        output = new StubOutput();
        startUI = new StartUI(output);
        memTracker = new MemTracker();
        memTracker.init();
    }

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "itemName", "itemDescription", "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new Exit(output));
        startUI.init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("itemName"));
        assertThat(memTracker.findAll().get(0).getDescription(), is("itemDescription"));
    }

    @Test
    public void whenReplaceItem() {
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        String replacedDesc = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, replacedDesc,
                                "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new Exit(output));
        startUI.init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new Exit(output));
        startUI.init(in, memTracker, actions);
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
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAllAction(output));
        actions.add(new Exit(output));
        String[] expected = {"Item1", "Item2", "Item3"};
        startUI.init(in, memTracker, actions);
        for (int i = 0; i < expected.length; i++) {
            assertThat(memTracker.findAll().get(i).getName(), is(expected[i]));
        }
        assertThat(output.toString(), is(
                 startUI.printLogo() + ls
                        + "Menu." + ls
                        + "0. ShowAllItems" + ls
                        + "1. Exit" + ls
                        + "=== Show all items ===" + ls
                        + items[0] + ls
                        + items[1] + ls
                        + items[2] + ls
                        + "Menu." + ls
                        + "0. ShowAllItems" + ls
                        + "1. Exit" + ls
                        + "Exit programme success!" + ls
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
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindItemByNameAction(output));
        actions.add(new Exit(output));
        String expected = "Found Item";
        startUI.init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(1).getName(), is(expected));
        assertThat(output.toString(), is(
                startUI.printLogo() + ls
                        + "Menu." + ls
                        + "0. FindItemByName" + ls
                        + "1. Exit" + ls
                        + "=== Find items by key in item name ===" + ls
                        + memTracker.findAll().get(1) + ls
                        + "Menu." + ls
                        + "0. FindItemByName" + ls
                        + "1. Exit" + ls
                        + "Exit programme success!" + ls
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
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindItemByIdAction(output));
        actions.add(new Exit(output));
        String expected = "Item2";
        startUI.init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(1).getName(), is(expected));
        assertThat(output.toString(), is(
                startUI.printLogo() + ls
                        + "Menu." + ls
                        + "0. FindItemById" + ls
                        + "1. Exit" + ls
                        + "=== Find item by Id ===" + ls
                        + memTracker.findAll().get(1) + ls
                        + "Menu." + ls
                        + "0. FindItemById" + ls
                        + "1. Exit" + ls
                        + "Exit programme success!" + ls
        ));
    }

    @Test
    public void whenInvalidExit() {
        Input in = new StubInput(
                new String[] {"7", "0"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit(output));
        startUI.init(in, memTracker, actions);
        assertThat(output.toString(), is(
                String.format(
                        startUI.printLogo()
                                + "%nMenu.%n"
                                + "0. Exit%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n"
                                + "0. Exit%n"
                                + "Exit programme success!%n"
                )
        ));
    }
}