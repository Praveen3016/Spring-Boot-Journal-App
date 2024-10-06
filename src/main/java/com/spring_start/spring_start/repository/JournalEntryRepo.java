package com.spring_start.spring_start.repository;

import com.spring_start.spring_start.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry , ObjectId> {

}
