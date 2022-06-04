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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class Controller {

  private final StudentManagerServiceImpl studentManagerService;

  @PostMapping
  public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto) {
    Student student;
    try {
      student = ConvertedDtoToModel.convertedToModel(studentDto);
    } catch (ParseException ex) {
      return ResponseEntity.status(500).body("Невалидная дата");
    }
    int id = studentManagerService.addStudent(student);
    if (id != 0) {
      return ResponseEntity.ok().body(id);
    } else {
      return ResponseEntity.status(500).body("Студент не добавлен");
    }
  }

  @GetMapping()
  public ResponseEntity<?> getStudents() {
    return ResponseEntity.ok().body(studentManagerService.getListStudents());
  }

  @DeleteMapping()
  public ResponseEntity<?> deleteStudent(@RequestParam int id){
    studentManagerService.deleteStudent(id);
    return ResponseEntity.ok().build();
  }

}
/**
 {
 "id": 0,
 "name": "fdsfdf",
 "birthdate": null,
 "group": "y113"
 }
 */
