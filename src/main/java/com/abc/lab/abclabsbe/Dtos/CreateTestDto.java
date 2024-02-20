package com.abc.lab.abclabsbe.Dtos;

import java.util.List;

import lombok.Data;

@Data
public class CreateTestDto {
  private String name;

  private String description;

  private Double price;

  private List<String> testLabels;
}
