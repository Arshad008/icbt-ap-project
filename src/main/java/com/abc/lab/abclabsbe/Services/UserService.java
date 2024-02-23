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
  private MailService mailService;

  @Autowired
  private MongoTemplate mongoTemplate;

  public User registerUser(Map<String, String> payload) {
    User newUser = new User();

    String email = payload.get("email");
    String firstName = payload.get("firstName");
    String lastName = payload.get("lastName");
    String password = payload.get("password");

    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setDateOfBirth(payload.get("dateOfBirth"));
    newUser.setGender(payload.get("gender"));
    newUser.setEmail(email);
    newUser.setPhoneNumber(payload.get("phoneNumber"));
    newUser.setPassword(password);
    newUser.setAddress(payload.get("address"));
    newUser.setRole("User");
    newUser.setCreatedAt(new Date());

    User result = userRepository.insert(newUser);

    String mailSubject = "Welcome " + firstName + " " + lastName;
    String mailBody = "Welcome " + firstName + " " + lastName + ",\n\n";

    mailBody += "Thank you for choosing us. Please find your Email & Password below.\n\n";
    mailBody += "Email: " + email + "\n";
    mailBody += "Password: " + password + "\n\n";
    mailBody += "Thank you!.";

    mailService.sendMail(email, mailSubject, mailBody);

    return result;
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
