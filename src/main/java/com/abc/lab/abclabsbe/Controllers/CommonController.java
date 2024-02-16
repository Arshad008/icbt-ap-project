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

import com.abc.lab.abclabsbe.Models.Staff;
import com.abc.lab.abclabsbe.Models.User;
import com.abc.lab.abclabsbe.Response.CustomErrorResponse;
import com.abc.lab.abclabsbe.Services.StaffService;
import com.abc.lab.abclabsbe.Services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CommonController {
  @Autowired
  private UserService userService;

  @Autowired
  private StaffService staffService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody Map<String, String> payload) {
    String email = payload.get("email");
    String password = payload.get("password");

    Optional<User> user = userService.loginUser(email, password);
    Optional<Staff> staff = staffService.loginStaff(email, password);

    if (user.isPresent()) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

    if (staff.isPresent()) {
      return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
        "Check your email and password");

    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }
}
