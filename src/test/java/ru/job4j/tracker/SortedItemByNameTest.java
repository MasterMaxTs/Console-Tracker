package ru.job4j.tracker;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SortedItemByNameTest {

    @Test
    public void whenSortedByIncreaseName() {
        MemTracker memTracker = new MemTracker();
        memTracker.add(new Item("Planning"));
        memTracker.add(new Item("Discussing"));
        memTracker.add(new Item("Coding"));
        List<Item> input = memTracker.findAll();
        input.sort(new SortedItemByName());
        assertThat(input.get(0).getName(), is("Coding"));
        assertThat(input.get(1).getName(), is("Discussing"));
        assertThat(input.get(2).getName(), is("Planning"));
    }

    @Test
    public void sortedByDecreaseName() {
        MemTracker memTracker = new MemTracker();
        memTracker.add(new Item("Planning"));
        memTracker.add(new Item("Coding"));
        memTracker.add(new Item("Discussing"));
        List<Item> input = memTracker.findAll();
        input.sort(new SortedItemByName().reversed());
        assertThat(input.get(0).getName(), is("Planning"));
        assertThat(input.get(1).getName(), is("Discussing"));
        assertThat(input.get(2).getName(), is("Coding"));
    }
}