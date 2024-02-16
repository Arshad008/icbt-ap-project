package com.abc.lab.abclabsbe.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abc.lab.abclabsbe.Models.User;




@Repository
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findById(String id);

  Optional<User> findByEmail(String email);

  Optional<User> findByPhoneNumber(String phoneNumber);
}
