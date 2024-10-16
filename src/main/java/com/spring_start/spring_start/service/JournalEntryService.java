package com.spring_start.spring_start.service;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.repository.JournalEntryRepo;
import com.spring_start.spring_start.repository.UsersRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepo journalEntryRepo ;
    @Autowired
    private UsersService usersService;


@Transactional
    public JournalEntry saveEntry(JournalEntry journalEntry, String username)
    {
        Users user = usersService.findByUserName(username);

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntryList().add(saved);
        usersService.saveUser(user);
        return journalEntry;
    }

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries()
    {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id)
    {
        return journalEntryRepo.findById(id);
    }

    public boolean deleteById(ObjectId id, String username)
    {
        try {
            Users user = usersService.findByUserName(username);
            boolean removed =  user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
            if(removed){
                usersService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        }catch(Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry" ,e);
        }


        return true ;
    }

    public boolean updateEntry(ObjectId id , JournalEntry updatingJE)
    {

        return true ;
    }
}
