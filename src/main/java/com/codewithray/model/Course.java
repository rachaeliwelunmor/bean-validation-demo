package com.codewithray.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "course")
public class Course extends BaseEntity {
    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @NotNull(message = "courseName is required")
    @Column(name = "course_name", nullable = false, unique = true)
    private String courseName;

    @NotNull(message = "teacher is required")
    @ManyToOne
    @JoinColumn(nullable = false, name = "teacher", referencedColumnName = "id")
    @JsonIgnoreProperties({"courses"})
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties({"courses"})
    private List<Student> students;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Course() {
    }

    public Course(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                "} " + super.toString();
    }
}
