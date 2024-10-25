package com.spring_start.spring_start.Entity;

import com.spring_start.spring_start.enume.Sentiment;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id ;
    @Indexed(unique = true)
    private String title ;
    private String content ;
    private LocalDateTime date ;
    private Sentiment sentiment ;

}
