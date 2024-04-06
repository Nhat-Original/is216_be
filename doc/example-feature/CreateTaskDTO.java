package com.github.nhatoriginal.spring.doc.example;

import lombok.Data;

@Data
public class CreateTaskDTO {
  private String title;
  private String description;
  private boolean completed;
}
