package com.abc.lab.abclabsbe.Services;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.abc.lab.abclabsbe.Models.Staff;
import com.abc.lab.abclabsbe.Repositories.StaffRepository;

@Service
public class StaffService {
  @Autowired
  private StaffRepository staffRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public Staff registerStaff(Map<String, String> payload) {
    Staff newStaff = new Staff();

    newStaff.setName(payload.get("name"));
    newStaff.setEmail(payload.get("email"));
    newStaff.setPhoneNumber(payload.get("phoneNumber"));
    newStaff.setPassword(payload.get("password"));
    newStaff.setRole("Staff");
    newStaff.setCreatedAt(new Date());

    return staffRepository.insert(newStaff);
  }

  public Optional<Staff> findById(String id) {
    return staffRepository.findById(id);
  }

  public Optional<Staff> findStaffByEmail(String email) {
    return staffRepository.findByEmail(email);
  }

  public Optional<Staff> loginStaff(String email, String password) {
    Staff staff = mongoTemplate
        .findOne(
            new Query().addCriteria(
                Criteria.where("email").is(email).and("password").is(password)),
            Staff.class);

    return Optional.ofNullable(staff);
  }

  public Staff initStaff() {
    Staff newStaff = new Staff();

    newStaff.setName("Arshad Staff");
    newStaff.setEmail("arshad@abc.com");
    newStaff.setPhoneNumber("0771673400");
    newStaff.setPassword("1234QWER");
    newStaff.setRole("Staff");
    newStaff.setCreatedAt(new Date());

    return staffRepository.insert(newStaff);
  }
}
