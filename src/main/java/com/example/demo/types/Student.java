package com.example.demo.types;

public class Student {
    public int id;
    public String name;
    public String family;
    public String patronymic;
    public int group;
    public String code;

    public Student(int id, String name, String family, String patronymic, int group, String code) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.patronymic = patronymic;
        this.group = group;
        this.code = code;
    }
}
