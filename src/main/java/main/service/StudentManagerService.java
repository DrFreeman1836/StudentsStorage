package main.service;

import java.util.List;
import main.model.Student;

public interface StudentManagerService {

  /**
   * Добавить студента
   *
   * @param student
   * @return id студента
   */
  int addStudent(Student student);

  /**
   * Удалить студента
   *
   * @param id
   */
  void deleteStudent(int id);

  /**
   * Вывести список студентов
   *
   * @return список студентов
   */
  List<Student> getListStudents();

}
