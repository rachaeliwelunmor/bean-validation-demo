package com.codewithray.service.jpaImpl;

import com.codewithray.exceptions.EntityNotFoundException;
import com.codewithray.model.Student;
import com.codewithray.repository.StudentRepository;
import com.codewithray.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student s) {
        s.setStudentID("STU" + (100 + new Random(System.currentTimeMillis()).nextInt(2000)));
        return studentRepository.save(s);
    }

    @Override
    public List<Student> get(int page, int size) {
        return studentRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " does not exist"));
    }

    @Override
    public Student update(Student s) {
        Student student = studentRepository.findById(s.getId()).orElseThrow(EntityNotFoundException::new);
        student.setEmail(s.getEmail());
        student.setFirstName(s.getFirstName());
        student.setLastName(s.getLastName());
        student.setMiddleName(s.getMiddleName().isBlank() ? null : s.getMiddleName());
        student.setGender(s.getGender());
        return student;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
