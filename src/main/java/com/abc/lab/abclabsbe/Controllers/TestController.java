package com.abc.lab.abclabsbe.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.lab.abclabsbe.Models.Test;
import com.abc.lab.abclabsbe.Services.TestService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
  @Autowired
  private TestService testService;

  @PostMapping
  public ResponseEntity<Test> createTest(@RequestBody Test test) {
    return new ResponseEntity<Test>(testService.creaTest(test), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Test>> getTests() {
    return new ResponseEntity<List<Test>>(testService.getTests(), HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Test> deleteTest(@RequestParam String id) {
    return new ResponseEntity<Test>(testService.deleteTest(id), HttpStatus.OK);
  }

}
