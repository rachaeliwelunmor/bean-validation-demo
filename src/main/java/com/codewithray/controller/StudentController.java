package com.codewithray.controller;

import com.codewithray.model.Student;
import com.codewithray.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity getStudents(@RequestParam(value = "page",  defaultValue = "0") int page, @RequestParam(value = "size",  defaultValue = "10") int size){
        return ResponseEntity.ok(studentService.get(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity getStudentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid Student student){
        return ResponseEntity.ok(studentService.add(student));
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Long id){
        studentService.delete(id);
        return ResponseEntity.ok("Successfully deleted Student with " + id);
    }
}
