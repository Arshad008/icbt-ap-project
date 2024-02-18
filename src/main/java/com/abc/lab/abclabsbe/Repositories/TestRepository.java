package com.abc.lab.abclabsbe.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abc.lab.abclabsbe.Models.Test;
import java.util.Optional;

public interface TestRepository extends MongoRepository<Test, String> {
  Optional<Test> findById(String id);
}
