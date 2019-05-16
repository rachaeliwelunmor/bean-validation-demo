package com.codewithray.controller;

import com.codewithray.model.Course;
import com.codewithray.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity getCourses(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(courseService.get(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity getCourseById(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PostMapping
    public ResponseEntity addCourse(@RequestBody @Valid Course course){
        return ResponseEntity.ok(courseService.add(course));
    }

    @PutMapping
    public ResponseEntity updateCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.update(course));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") Long id){
        return ResponseEntity.ok("Successfully deleted course with id " + id);
    }
}
