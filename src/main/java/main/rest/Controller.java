package main.rest;

import java.text.ParseException;
import lombok.RequiredArgsConstructor;
import main.dto.StudentDto;
import main.model.Student;
import main.service.ConvertedDtoToModel;
import main.service.impl.StudentManagerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class Controller {

  private final StudentManagerServiceImpl studentManagerService;

  @PostMapping
  public ResponseEntity<?> addStudent(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "birthdate", required = false) String birthdate,
      @RequestParam(name = "group", required = false) String group) {
    Student student;
    try {
      student = ConvertedDtoToModel.convertedToModel(StudentDto.builder()
          .name(name)
          .birthdate(birthdate)
          .group(group).build());
    } catch (ParseException ex) {
      return ResponseEntity.status(500).body("Невалидная дата");
    }
    int id = studentManagerService.addStudent(student);
    if (id != 0) {
      return ResponseEntity.ok().body("Студент сохранен");
    } else {
      return ResponseEntity.status(500).body("Студент не сохранен");
    }
  }

  @GetMapping()
  public ResponseEntity<?> getStudents() {
    return ResponseEntity.ok().body(studentManagerService.getListStudents());
  }

  @DeleteMapping()
  public ResponseEntity<?> deleteStudent(@RequestParam int id) {
    studentManagerService.deleteStudent(id);
    return ResponseEntity.ok().build();
  }

}
