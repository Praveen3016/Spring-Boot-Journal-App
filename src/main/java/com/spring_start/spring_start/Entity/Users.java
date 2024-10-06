package com.spring_start.spring_start.Entity;


import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "users")
@Data
public class Users {

    @Id
    private ObjectId id ;
    @Indexed(unique = true)
    @NonNull
    private String username ;
    @NonNull
    private String password ;
   @DBRef
    private List<JournalEntry> journalEntryList = new ArrayList<>();

}
