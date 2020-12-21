package com.TopScoreRanking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface RankingRepository extends JpaRepository<Ranking, Long> {
	Page<Ranking> findByPlayerIn(List<String> player, Pageable pageable);
	Page<Ranking> findByPlayerInAndTimeAfter(List<String> player, LocalDateTime time, Pageable pageable);
	Page<Ranking> findByPlayerInAndTimeBefore(List<String> player, LocalDateTime time, Pageable pageable);
	
//	@Query("SELECT new RankingHistory(rk.player as " + RankingHistory.PLAYER_
//		+ ", max(rk.score) as " + RankingHistory.TOP_SCORE 
//		+ ", min(rk.score) as " + RankingHistory.LOW_SCORE 
//		+ ", avg(rk.score) as " + RankingHistory.AVG_SCORE  
//		+ ") FROM Ranking rk")
	//List<Map<String, Object>> fetchByPlayerWithMapResult(String player);
	
	//@Query("SELECT new RankingHistory(rk.player, max(rk.score), min(rk.score), avg(rk.score)) FROM Ranking rk Group by rk.player")
	@Query("SELECT rk.player FROM Ranking rk")
	Page<RankingHistory> getPlayer(String player, Pageable pageable);
}
