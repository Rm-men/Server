package com.example.demo.types;

public class Student {
    private final int id;
    private final String name;
    private final String family;
    private final String patronymic;
    private final int group;
    private final String code;

    public Student(int id, String name, String family, String patronymic, int group, String code) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.patronymic = patronymic;
        this.group = group;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getGroup() {
        return group;
    }

    public String getCode() {
        return code;
    }
}
