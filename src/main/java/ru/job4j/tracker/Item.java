package ru.job4j.tracker;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String name;
    private LocalDateTime created;
    private String description;

    public Item(@NonNull String name) {
        this.name = name;
        this.created = LocalDateTime.now();
    }

    public Item(@NonNull String name, String description) {
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
    }

    public Item(int id, @NonNull String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %s, name: %s, created: %s", id, name, FORMATTER.format(created)
        );
    }
}

