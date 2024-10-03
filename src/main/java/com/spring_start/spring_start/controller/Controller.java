package com.spring_start.spring_start.controller;

import com.spring_start.spring_start.Entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/journal")
public class Controller {

    Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> gellAll()
    {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createNew(@RequestBody JournalEntry journalEntry)
    {
        journalEntries.put(journalEntry.getId() , journalEntry);
        return true ;
    }

    @GetMapping("id/{id}")
    public JournalEntry gellOne(@PathVariable Long id)
    {
        return journalEntries.get(id) ;
    }

    @DeleteMapping("id/{id}")
    public boolean deleteOne(@PathVariable Long id)
    {
        journalEntries.remove(id);
        return  true ;
    }

    @PutMapping("id/{id}")
    public JournalEntry deleteOne(@PathVariable Long id , @RequestBody JournalEntry journalEntry)
    {
        return journalEntries.put(id , journalEntry) ;
    }

//    @DeleteMapping
//    public boolean deleteAll()
//    {
//        journalEntries.remove();
//        return true ;
//    }
//


}
