package com.TopScoreRanking;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface RankingHistory {
	String getPlayer();
	
//	@Value("#{topScore}")
//    String getTopScore();
//	
//	@Value("#{lowScore}")
//    String getLowScore();
//	
//	@Value("#{avgScore}")
//    String getAvgScore();
	
	//List<RankingPlayerScoreList> getScoreList();
}

//import java.util.Objects;
//
//class RankingHistory {
//
//	private String player;
//	private int topScore;
//	private int lowScore;
//	private double avgScore;
//	
//	RankingHistory() {}
//	
//	RankingHistory(String player, int topScore, int lowScore, double avgScore) {
//      this.player = player;
//      this.topScore = topScore;
//      this.lowScore = lowScore;
//      this.avgScore = avgScore;
//  }
//	
//	public String getPlayer() {
//	    return this.player;
//	  }
//	
//	public int getTopScore() {
//	    return this.topScore;
//	  }
//	
//	public int getLowScore() {
//	    return this.lowScore;
//	  }
//	
//	public double getAvgScore() {
//	    return this.avgScore;
//	  }
//	
//	public void setPlayer(String player) {
//	    this.player = player;
//	  }
//	
//	public void setTopScore(int topScore) {
//	    this.topScore = topScore;
//	  }
//	
//	public void setLowScore(int lowScore) {
//	    this.lowScore = lowScore;
//	  }
//	
//	public void setAvgScore(double avgScore) {
//	    this.avgScore = avgScore;
//	  }
//	
//	@Override
//	  public boolean equals(Object o) {
//
//	    if (this == o)
//	      return true;
//	    if (!(o instanceof RankingHistory))
//	      return false;
//	    RankingHistory rank = (RankingHistory) o;
//	    return Objects.equals(this.player, rank.player) && Objects.equals(this.topScore, rank.topScore)
//	        && Objects.equals(this.lowScore, rank.lowScore) && Objects.equals(this.avgScore, rank.avgScore);
//	  }
//
//	  @Override
//	  public int hashCode() {
//	    return Objects.hash(this.player, this.topScore, this.lowScore, this.avgScore);
//	  }
//
//	  @Override
//	  public String toString() {
//	    return "Rank{" + "player=" + this.player + ", topScore='" + this.topScore + '\'' + ", lowScore='" + this.lowScore + '\'' + ", avgScore='" + this.avgScore + '\'' + '}';
//	  }
//}