package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.service.JournalEntryService;
import com.spring_start.spring_start.service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/journal")
public class Controller {

    @Autowired
    private JournalEntryService journalEntryService ;
    @Autowired
    private UsersService usersService ;


    @GetMapping()
    public ResponseEntity<?> gellAllEntryOfUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
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

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            journalEntryService.saveEntry(journalEntry , username);
            return new ResponseEntity<>(journalEntry , HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;

        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> gellOne(@PathVariable ObjectId id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = usersService.findByUserName(username);
       List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
       if(!collect.isEmpty())
       {
           Optional<JournalEntry> journalEntry = journalEntryService.getById(id);
           if(journalEntry.isPresent()){
               return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
           }
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteOne(@PathVariable ObjectId id )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return journalEntryService.deleteById(id , username)  ;
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id ,@RequestBody JournalEntry updatingJE )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = usersService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty())
        {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(id);
            if(journalEntry.isPresent())
            {   JournalEntry old = journalEntry.get();
                old.setTitle(updatingJE.getTitle() != null && !updatingJE.getTitle().equals("")? updatingJE.getTitle() : old.getTitle() );
                old.setContent(updatingJE.getContent() != null && !updatingJE.getContent().equals("") ? updatingJE.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old , HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

}
