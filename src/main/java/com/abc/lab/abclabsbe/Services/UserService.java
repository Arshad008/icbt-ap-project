package com.abc.lab.abclabsbe.Services;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.abc.lab.abclabsbe.Models.User;
import com.abc.lab.abclabsbe.Repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public User registerUser(Map<String, String> payload) {
    User newUser = new User();

    newUser.setFirstName(payload.get("firstName"));
    newUser.setLastName(payload.get("lastName"));
    newUser.setDateOfBirth(payload.get("dateOfBirth"));
    newUser.setGender(payload.get("gender"));
    newUser.setEmail(payload.get("email"));
    newUser.setPhoneNumber(payload.get("phoneNumber"));
    newUser.setPassword(payload.get("password"));
    newUser.setAddress(payload.get("address"));
    newUser.setRole("User");
    newUser.setCreatedAt(new Date());

    return userRepository.insert(newUser);
  }

  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<User> loginUser(String email, String password) {
    User user = mongoTemplate
        .findOne(
            new Query().addCriteria(
                Criteria.where("email").is(email).and("password").is(password)),
            User.class);

    return Optional.ofNullable(user);
  }
}
