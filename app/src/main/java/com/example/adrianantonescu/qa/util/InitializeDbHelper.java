package com.example.adrianantonescu.qa.util;

import com.example.adrianantonescu.qa.database.DatabaseRepository;

public class InitializeDbHelper {
    DatabaseRepository databaseRepository;

    public InitializeDbHelper(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public void insertInDb() {
        Student stud1 = new Student("student1", "student", "Adrian",
                "Popescu", "asdf1@ex.com", "asdf", "Cibernetica", 1, "A", 1005);
        stud1.setId(5L);
        Student stud2 = new Student("student2", "student", "Adriana",
                "Vasile", "asdf2@ex.com", "asdf", "Cibernetica", 1, "A", 1005);
        stud2.setId(6L);

        Teacher t1 = new Teacher("teacher12", "teacher", "Adrian", "Vasile",
                "teacherx1@example.com", "biobio");
        t1.setId(20L);
        Teacher t2 = new Teacher("teacher21", "teacher", "Andrei", "Xyz",
                "teacherx2@example.com", "biobio");
        t2.setId(21L);

        Course c1 = new Course(1L, "Cibernetica", 5);
        Course c2 = new Course(2L, "DAM", 5);
        Course c3 = new Course(3L, "Java", 3);

        databaseRepository.open();
        databaseRepository.insertStudent(stud1);
        databaseRepository.insertStudent(stud2);
        databaseRepository.insertTeacher(t1);
        databaseRepository.insertTeacher(t2);
//        databaseRepository.insertCourse(c1);
//        databaseRepository.insertCourse(c2);
//        databaseRepository.insertCourse(c3);
//        databaseRepository.insertDistribution(t1, c1);
//        databaseRepository.insertDistribution(t2,c2);
//        databaseRepository.insertDistribution(t2,c2);
//        databaseRepository.insertStudentScore(stud1, c1, 80);
//        databaseRepository.insertStudentScore(stud2, c2, 20);
        databaseRepository.close();

    }
}
