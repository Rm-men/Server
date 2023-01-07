package com.example.demo.types;

public class Student {
    private int id;
    private String name;
    private String family;
    private String patronymic;
    private int group;
    private String code;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
