package main.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {

  private String name;

  private String birthdate;

  private String group;

}
