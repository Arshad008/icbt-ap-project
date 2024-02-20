package com.abc.lab.abclabsbe.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {
  @Id
  private String id;

  @DocumentReference
  private User user;

  @DocumentReference
  private Test test;

  private Date createdAt;

  private Date requestedDate;

  private String status;

  private Long number;

  private Date appointmentDate;
}
