package com.codewithray.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {
    @Column(name = "staff_id", unique = true, nullable = false)
    private String staffId;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Teacher() {
    }

    public Teacher(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "staffId='" + staffId + '\'' +
                ", courses=" + courses +
                "} " + super.toString();
    }
}
