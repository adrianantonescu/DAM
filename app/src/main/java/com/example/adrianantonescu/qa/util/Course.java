package com.example.adrianantonescu.qa.util;

public class Course {
    private Long id;
    private String name;
    private int credit;

    public Course(Long id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                '}';
    }
}
