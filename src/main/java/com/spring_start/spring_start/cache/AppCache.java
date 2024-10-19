package com.spring_start.spring_start.cache;

import com.spring_start.spring_start.Entity.ConfigJournalAppEntity;
import com.spring_start.spring_start.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AppCache {

    public Map<String, String> APP_CACHE;

    @Autowired // Add this to ensure Spring injects the repository
    private ConfigJournalAppRepo configJournalAppRepo;

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll(); // This was causing the NullPointerException
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
            log.info("api changed : {}" , APP_CACHE.get("weather_api"));
        }
    }
}

