package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.trackers.SqlTracker;
import ru.job4j.tracker.trackers.Store;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store memTracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, memTracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }
    }

    private static String loadSysEnvIfNullThenConfig(String sysEnv,
                                                     String key,
                                                     Properties config) {
        String value = System.getenv(sysEnv);
        if (value == null) {
            value = config.getProperty(key);
        }
        return value;
    }

    private static Connection loadConnection()
                                    throws ClassNotFoundException, SQLException {
        var config = new Properties();
        try (InputStream in = StartUI.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String url = loadSysEnvIfNullThenConfig("JDBC_URL", "url", config);
        String username = loadSysEnvIfNullThenConfig("JDBC_USERNAME", "username", config);
        String password = loadSysEnvIfNullThenConfig("JDBC_PASSWORD", "password", config);
        String driver = loadSysEnvIfNullThenConfig("JDBC_DRIVER", "driver-class-name", config);
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) throws Exception {
        Output output = new ConsoleOutput();
        Input input =
                new ValidateInput(output, new ConsoleInput());
        try (Store tracker = new SqlTracker(loadConnection())) {
            tracker.init();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction(output));
            actions.add(new ShowAllAction(output));
            actions.add(new ReplaceAction(output));
            actions.add(new DeleteAction(output));
            actions.add(new FindItemByIdAction(output));
            actions.add(new FindItemByNameAction(output));
            actions.add(new Exit(output));
            new StartUI(output).init(input, tracker, actions);
        }
    }
}
