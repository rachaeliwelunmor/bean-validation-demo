package com.codewithray.service.jpaImpl;

import com.codewithray.exceptions.EntityNotFoundException;
import com.codewithray.model.Teacher;
import com.codewithray.repository.TeacherRepository;
import com.codewithray.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher add(Teacher teacher) {
        teacher.setStaffId("STA"+(100 + new Random(System.currentTimeMillis()).nextInt(2000)));
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> get(int page, int size) {
        return teacherRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " does not exist"));
    }

    @Override
    public Teacher update(Teacher teacher) {
        Teacher t = teacherRepository.findById(teacher.getId()).orElseThrow(EntityNotFoundException::new);
        t.setEmail(teacher.getEmail());
        t.setFirstName(teacher.getFirstName());
        t.setLastName(teacher.getLastName());
        t.setMiddleName(teacher.getMiddleName().isBlank() ? null : teacher.getMiddleName());
        t.setGender(teacher.getGender());
        return t;
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
