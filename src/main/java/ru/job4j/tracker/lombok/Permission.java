package ru.job4j.tracker.lombok;

import lombok.*;

import java.util.List;

@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
public class Permission {

    @EqualsAndHashCode.Include
    private int id;
    private String name;

    @Singular("rules")
    private List<String> rules;
}
