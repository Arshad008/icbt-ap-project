package com.abc.lab.abclabsbe.Services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    newStaff.setSubRole(payload.get("subRole"));
    newStaff.setStatus("Active");
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
                Criteria.where("email").is(email).and("password").is(password).and("status").is("Active")),
            Staff.class);

    return Optional.ofNullable(staff);
  }

  public List<Staff> getStaffList() {
    return staffRepository.findAll();
  }

  public Staff updateStatus(String id, String status) {
    Query query = new Query(Criteria.where("_id").is(id));
    Update update = new Update().set("status", status);
    FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

    return mongoTemplate.findAndModify(query, update, options, Staff.class);
  }

  public Staff initStaff() {
    Staff newStaff = new Staff();

    newStaff.setName("Arshad Staff");
    newStaff.setEmail("arshad@abc.com");
    newStaff.setPhoneNumber("0771673400");
    newStaff.setPassword("1234QWER");
    newStaff.setRole("Staff");
    newStaff.setSubRole("Admin");
    newStaff.setStatus("Active");
    newStaff.setCreatedAt(new Date());

    return staffRepository.insert(newStaff);
  }
}
