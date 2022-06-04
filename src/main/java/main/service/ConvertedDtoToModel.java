package main.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import main.dto.StudentDto;
import main.model.Student;

public class ConvertedDtoToModel {

  private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

  public static Student convertedToModel(StudentDto studentDto) throws ParseException {
    return Student.builder()
        .name(studentDto.getName())
        .birthdate(format.parse(studentDto.getBirthdate()))
        .group(studentDto.getGroup())
        .build();
  }

}
