package com.example.adrianantonescu.qa.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Colectiv implements Serializable {
    private String subject;
    private Integer group;
    private String series;
    private Integer year;
    private List<Stud> students;

    public Colectiv() {
    }

    public Colectiv(String subject, Integer group, String series, Integer year, List<Stud> students) {
        this.subject = subject;
        this.group = group;
        this.series = series;
        this.year = year;
        this.students = students;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Stud> getStudents() {
        return students;
    }

    public void setStudents(Stud stud)
    {
        if(students==null)
            students=new ArrayList<>();
        if(stud!=null)
            students.add(stud);
    }
    public void setStudents(List<Stud> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder std=new StringBuilder();
        for(int i=0;i<this.students.size();i++)
            std.append(this.students.get(i));
        return this.subject+" - "+this.group+", "+this.series+", "+this.year+"\nStudenti: \n"+std.toString()+"\n";
    }
}
