package com.codewithray.controller;

import com.codewithray.model.Teacher;
import com.codewithray.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity getTeachers(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(teacherService.get(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity getTeacherById(@PathVariable("id") Long id){
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity addTeacher(@RequestBody Teacher teacher){
        return ResponseEntity.ok(teacherService.add(teacher));
    }

    @PutMapping
    public ResponseEntity updateTeacher(@RequestBody Teacher teacher){
        return ResponseEntity.ok(teacherService.update(teacher));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTeacher(@PathVariable("id") Long id){
        teacherService.delete(id);
        return ResponseEntity.ok("Successfully deleted teacher with id " + id);
    }
}
