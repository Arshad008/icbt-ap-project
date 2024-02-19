package com.abc.lab.abclabsbe.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abc.lab.abclabsbe.Models.Appointment;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
  Optional<Appointment> findByNumber(String number);

  Optional<Appointment> findByUserId(String userId);
}
