package com.abc.lab.abclabsbe.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.lab.abclabsbe.Models.Staff;
import com.abc.lab.abclabsbe.Repositories.StaffRepository;
import com.abc.lab.abclabsbe.Response.CustomErrorResponse;
import com.abc.lab.abclabsbe.Services.StaffService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
  @Autowired
  private StaffService staffService;

  @Autowired
  private StaffRepository staffRepository;

  @PostMapping
  public ResponseEntity<Object> registerStaff(@RequestBody Map<String, String> payload) {
    Optional<Staff> emailMatchingStaff = staffRepository.findByEmail(payload.get("email"));
    Optional<Staff> phoneNumberMatchingStaff = staffRepository.findByPhoneNumber(payload.get("phoneNumber"));

    if (emailMatchingStaff.isPresent()) {
      CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
          "Staff with this email already exists");

      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    if (phoneNumberMatchingStaff.isPresent()) {
      CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
          "Staff with this phone number already exists");

      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(staffService.registerStaff(payload), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Staff>> getStaffList() {
    return new ResponseEntity<List<Staff>>(staffService.getStaffList(), HttpStatus.OK);
  }

  @PutMapping("/updateStatus")
  public ResponseEntity<Staff> updateStatus(@RequestBody Map<String, String> payload) {
    return new ResponseEntity<Staff>(staffService.updateStatus(payload.get("id"), payload.get("status")),
        HttpStatus.OK);
  }

  @PostMapping("/init")
  public ResponseEntity<Object> initStaff() {
    Optional<Staff> emailMatchingStaff = staffRepository.findByEmail("arshad@abc.com");

    if (emailMatchingStaff.isPresent()) {
      CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT,
          "Staff with this email already exists");

      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(staffService.initStaff(), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Staff>> getUserById(@PathVariable String id) {
    return new ResponseEntity<Optional<Staff>>(staffService.findById(id), HttpStatus.OK);
  }
}
