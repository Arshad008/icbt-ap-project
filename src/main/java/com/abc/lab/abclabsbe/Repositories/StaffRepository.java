package com.abc.lab.abclabsbe.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abc.lab.abclabsbe.Models.Staff;


@Repository
public interface StaffRepository extends MongoRepository<Staff, String> {
  Optional<Staff> findById(String id);

  Optional<Staff> findByEmail(String email);

  Optional<Staff> findByPhoneNumber(String phoneNumber);
}
