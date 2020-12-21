package com.TopScoreRanking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  
  @Bean
  CommandLineRunner initDatabase(RankingRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Ranking("PLAYER1", 100, LocalDateTime.parse("2020-12-20 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER2", 200, LocalDateTime.parse("2020-12-19 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER3", 300, LocalDateTime.parse("2020-12-18 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER4", 400, LocalDateTime.parse("2020-12-17 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER5", 500, LocalDateTime.parse("2020-12-16 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER6", 600, LocalDateTime.parse("2020-12-15 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER7", 700, LocalDateTime.parse("2020-12-14 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER8", 800, LocalDateTime.parse("2020-12-13 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER9", 900, LocalDateTime.parse("2020-12-12 17:46:30", formatter))));
      log.info("Preloading " + repository.save(new Ranking("PLAYER10", 1000, LocalDateTime.parse("2020-12-11 17:46:30", formatter))));
    };
  }
}