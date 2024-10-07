package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.service.JournalEntryService;
import com.spring_start.spring_start.service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/journal")
public class Controller {

    @Autowired
    private JournalEntryService journalEntryService ;
    @Autowired
    private UsersService usersService ;

    Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/{username}")
    public ResponseEntity<?> gellAllJournalEntryOfUser(@PathVariable String username)
    {
        Users user = usersService.findByUserName(username);
        List<JournalEntry> allEntryOfUser = user.getJournalEntryList();
        if(allEntryOfUser != null)
        {
            return new ResponseEntity<>(allEntryOfUser , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry , @PathVariable String username)
    {
        try {
            journalEntryService.saveEntry(journalEntry , username);
            return new ResponseEntity<>(journalEntry , HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;

        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> gellOne(@PathVariable ObjectId id)
    {
       Optional<JournalEntry> journalEntry = journalEntryService.getById(id);

       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
       }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{username}/{id}")
    public boolean deleteOne(@PathVariable ObjectId id , @PathVariable String username)
    {

        return journalEntryService.deleteById(id , username)  ;
    }

    @PutMapping("id/{username}/{id}")
    public ResponseEntity<JournalEntry> updateEntry(
            @PathVariable ObjectId id ,
            @RequestBody JournalEntry updatingJE,
            @PathVariable String username
            )
    {
        JournalEntry old = journalEntryService.getById(id).orElse(null);
        if(old != null)
        {
            old.setTitle(updatingJE.getTitle() != null && !updatingJE.getTitle().equals("")? updatingJE.getTitle() : old.getTitle() );
            old.setContent(updatingJE.getContent() != null && !updatingJE.getContent().equals("") ? updatingJE.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old , HttpStatus.CREATED);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);


    }

}
