package com.jsu.bean;

public class Classes {
    private String classesName;
    private int id;

    public Classes(String classesName, int id) {
        this.classesName = classesName;
        this.id = id;
    }

    public Classes() {
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
