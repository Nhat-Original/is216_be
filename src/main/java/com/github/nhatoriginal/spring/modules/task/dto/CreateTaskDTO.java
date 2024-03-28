package com.github.nhatoriginal.spring.modules.task.dto;

import lombok.Data;

@Data
public class CreateTaskDTO {
  private String title;
  private String description;
  private boolean completed;
}
