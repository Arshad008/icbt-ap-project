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

import com.abc.lab.abclabsbe.Dtos.UpdateAppointmentTestDataDto;
import com.abc.lab.abclabsbe.Models.Appointment;
import com.abc.lab.abclabsbe.Models.Test;
import com.abc.lab.abclabsbe.Models.User;
import com.abc.lab.abclabsbe.Repositories.TestRepository;
import com.abc.lab.abclabsbe.Repositories.UserRepository;
import com.abc.lab.abclabsbe.Services.AppointmentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
  @Autowired
  private AppointmentService appointmentService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestRepository testRepository;

  @GetMapping("/{appointmentNumber}")
  public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable Long appointmentNumber) {
    return new ResponseEntity<Optional<Appointment>>(appointmentService.getAppointmentByNumber(appointmentNumber),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Object> createAppointment(@RequestBody Map<String, String> payload) {
    String date = payload.get("date");
    String testId = payload.get("testId");
    String userId = payload.get("userId");

    Optional<User> userOptional = userRepository.findById(userId);
    Optional<Test> testOptional = testRepository.findById(testId);

    if (userOptional.isPresent() & testOptional.isPresent()) {
      User user = userOptional.get();
      Test test = testOptional.get();

      return new ResponseEntity<Object>(appointmentService.bookAppointment(date, test, user), HttpStatus.OK);
    }

    return new ResponseEntity<Object>(new Object(), HttpStatus.OK);
  }

  @PostMapping("/user/list")
  public ResponseEntity<List<Appointment>> getAppointmentsForUser(@RequestBody Map<String, String> payload) {
    String userId = payload.get("userId");

    return new ResponseEntity<List<Appointment>>(appointmentService.getAppointmentsForUser(userId), HttpStatus.OK);
  }

  @PostMapping("/admin/list")
  public ResponseEntity<List<Appointment>> getAppointmentsForAdmin(@RequestBody Map<String, String> payload) {
    String dateString = payload.get("date");

    return new ResponseEntity<List<Appointment>>(appointmentService.getAppointmentsForAdmin(dateString), HttpStatus.OK);
  }

  @PostMapping("/admin/bookAppointment")
  public ResponseEntity<Object> confirmAppointment(@RequestBody Map<String, String> payload) {
    String dateString = payload.get("date");
    String id = payload.get("id");

    return new ResponseEntity<Object>(appointmentService.confirmAppointment(id, dateString), HttpStatus.OK);
  }

  @PutMapping("/admin/updateDoctor")
  public ResponseEntity<Appointment> updateAppointmentDoctor(@RequestBody Map<String, String> payload) {
    String id = payload.get("id");
    String doctorName = payload.get("doctorName");

    return new ResponseEntity<Appointment>(appointmentService.updateAppointmentDoctor(id, doctorName), HttpStatus.OK);
  }

  @PutMapping("/admin/updateStatus")
  public ResponseEntity<Appointment> updateAppointmentStatus(@RequestBody Map<String, String> payload) {
    String id = payload.get("id");
    String status = payload.get("status");

    return new ResponseEntity<Appointment>(appointmentService.updateStatus(id, status), HttpStatus.OK);
  }

  @PutMapping("/admin/updateTestData")
  public ResponseEntity<Appointment> updateTestData(
      @RequestBody UpdateAppointmentTestDataDto appointmentTestDataDto) {
    String id = appointmentTestDataDto.getId();
    List<Object> testData = appointmentTestDataDto.getTestData();

    return new ResponseEntity<Appointment>(appointmentService.updateTestData(id, testData), HttpStatus.OK);
  }

  @PostMapping("/admin/getReportData")
  public ResponseEntity<List<Appointment>> getReportData(@RequestBody Map<String, String> payload) {
    String start = payload.get("start");
    String end = payload.get("end");

    return new ResponseEntity<List<Appointment>>(appointmentService.getReportData(start, end), HttpStatus.OK);
  }
}
