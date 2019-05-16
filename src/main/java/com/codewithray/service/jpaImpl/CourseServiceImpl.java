package com.codewithray.service.jpaImpl;

import com.codewithray.exceptions.EntityNotFoundException;
import com.codewithray.model.Course;
import com.codewithray.repository.CourseRepository;
import com.codewithray.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course add(Course course) {
        course.setCourseCode("CSE"+(100 + new Random(System.currentTimeMillis()).nextInt(2000)));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> get(int page, int size) {
        return courseRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " does not exist."));
    }

    @Override
    public Course update(Course course) {
        Course c = courseRepository.findById(course.getId()).orElseThrow(EntityNotFoundException::new);
        c.setTeacher(course.getTeacher());
        return c;
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
