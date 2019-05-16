package com.codewithray.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends Person {
    @Column(name = "student_id", unique = true, nullable = false)
    private String studentID;

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id", table = "student"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", table = "course"))
    private List<Course> courses;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
