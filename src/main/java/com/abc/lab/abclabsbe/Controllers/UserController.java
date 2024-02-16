package com.abc.lab.abclabsbe.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.lab.abclabsbe.Models.User;
import com.abc.lab.abclabsbe.Repositories.UserRepository;
import com.abc.lab.abclabsbe.Response.CustomErrorResponse;
import com.abc.lab.abclabsbe.Services.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<Object> registerUser(@RequestBody Map<String, String> payload) {
    Optional<User> emailMatchingUser = userRepository.findByEmail(payload.get("email"));
    Optional<User> phoneNumberMatchingUser = userRepository.findByPhoneNumber(payload.get("phoneNumber"));

    if (emailMatchingUser.isPresent()) {
      CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
          "User with this email already exists");

      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    if (phoneNumberMatchingUser.isPresent()) {
      CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
          "User with this phone number already exists");

      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(userService.registerUser(payload), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {
    return new ResponseEntity<Optional<User>>(userService.findById(id), HttpStatus.OK);
  }
}
