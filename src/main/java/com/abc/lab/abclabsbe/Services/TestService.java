package com.abc.lab.abclabsbe.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.abc.lab.abclabsbe.Dtos.tests.CreateTestDto;
import com.abc.lab.abclabsbe.Models.Test;
import com.abc.lab.abclabsbe.Repositories.TestRepository;

@Service
public class TestService {
  @Autowired
  private TestRepository testRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public Test createTest(CreateTestDto reqData) {
    Test test = new Test();

    test.setName(reqData.getName());
    test.setDescription(reqData.getDescription());
    test.setPrice(reqData.getPrice());
    test.setStatus("Active");
    test.setTestLabels(reqData.getTestLabels());

    return testRepository.insert(test);
  }

  public List<Test> getTests() {
    Query query = new Query(Criteria.where("status").is("Active"));

    return mongoTemplate.find(query, Test.class);
  }

  public Test deleteTest(String id) {
    Query query = new Query(Criteria.where("_id").is(id));
    Update update = new Update().set("status", "Inactive");
    FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

    return mongoTemplate.findAndModify(query, update, options, Test.class);
  }
}
