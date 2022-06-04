package main.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.model.Student;
import main.repo.StudentRepository;
import main.service.StudentManagerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentManagerServiceImpl implements StudentManagerService {

  private final StudentRepository studentRepository;


  @Override
  public int addStudent(Student student) {
    return studentRepository.save(student).getId();
  }

  @Override
  public void deleteStudent(int id) {
    studentRepository.deleteById(id);
  }

  @Override
  public List<Student> getListStudents() {
    return studentRepository.findAll();
  }
}
