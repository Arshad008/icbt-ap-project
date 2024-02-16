package com.abc.lab.abclabsbe.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "staffs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Staff {
  @Id
  private String id;

  private String name;

  private String email;

  private String phoneNumber;

  private String password;

  private Date createdAt;

  private String role;
}
