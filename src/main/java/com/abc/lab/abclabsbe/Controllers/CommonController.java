package com.abc.lab.abclabsbe.Controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.lab.abclabsbe.Models.User;
import com.abc.lab.abclabsbe.Response.CustomErrorResponse;
import com.abc.lab.abclabsbe.Services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CommonController {
  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody Map<String, String> payload) {
    String email = payload.get("email");
    String password = payload.get("password");
    String role = payload.get("role");

    if (role.equals("User")) {
      Optional<User> user = userService.loginUser(email, password);

      if (user.isPresent()) {
        return new ResponseEntity<>(user, HttpStatus.OK);
      } else {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
            "Check your email and password");

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
      }
    }

    return new ResponseEntity<>(payload, HttpStatus.OK);
  }
}
