package com.spring_start.spring_start.schedular;

import com.spring_start.spring_start.Entity.JournalEntry;
import com.spring_start.spring_start.Entity.Users;
import com.spring_start.spring_start.cache.AppCache;
import com.spring_start.spring_start.enume.Sentiment;
import com.spring_start.spring_start.repository.UserRepoImpl;
import com.spring_start.spring_start.service.EmailSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private EmailSevice emailService;

    // Uncomment the following line to schedule this task
    // @Scheduled(cron = "0 0 9 ? * SUN *")
    public void fetchUsersAndSendMail() {
        List<Users> users = userRepoImpl.GetUserForSA();
        for (Users user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntryList();
            List<Sentiment> filteredEntries = journalEntries.stream()
                    .filter(entry -> entry.getDate() != null && entry.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment)
                    .collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : filteredEntries) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                emailService.SendMail(user.getEmail(), "Sentiment for last 7 days", "Most frequent sentiment: " + mostFrequentSentiment);
            }
        }
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ? *")
    public void clearAppCache() {
        appCache.init();
    }
}
