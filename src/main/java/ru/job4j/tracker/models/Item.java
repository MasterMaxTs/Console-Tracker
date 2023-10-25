package ru.job4j.tracker.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Модель данных Заявка
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {

    /**
     * Зависимость от объекта DateTimeFormatter
     */
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Уникальный идентификатор заявки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Название заявки
     */
    @NonNull
    private String name;

    /**
     * Локальное время создания заявки
     */
    private LocalDateTime created;

    /**
     * Описание заявки
     */
    private String description;

    /**
     * Конструктор
     * @param name название заявки
     */
    public Item(@NonNull String name) {
        this.name = name;
        this.created = LocalDateTime.now();
    }

    /**
     * Конструктор
     * @param name название заявки
     * @param description описание заявки
     */
    public Item(@NonNull String name, String description) {
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
    }

    /**
     * Конструктор
     * @param id идентификатор заявки
     * @param name название заявки
     * @param created локальное время создания заявки
     */
    public Item(int id, @NonNull String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    /**
     * Переопределение метода toString()
     * @return строковое представление объекта в виде заявки
     */
    @Override
    public String toString() {
        return String.format("id: %s, name: %s, created: %s%ndescription: %s",
                id, name, FORMATTER.format(created), description);
    }
}

