package com.TopScoreRanking;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
class Ranking {

  private @Id @GeneratedValue Long id;
  private String player;
  private int score;
  @JsonFormat(pattern="yyyyMMddHHmmss")
  private LocalDateTime time;
  
  Ranking() {}

  Ranking(String player, int score, LocalDateTime time) {
    this.player = player;
    this.score = score;
    this.time = time;
  }

  public Long getId() {
    return this.id;
  }

  public String getPlayer() {
    return this.player;
  }

  public Integer getScore() {
    return this.score;
  }
  
  public LocalDateTime getTime() {
    return this.time;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
  
  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Ranking))
      return false;
    Ranking rank = (Ranking) o;
    return Objects.equals(this.id, rank.id) && Objects.equals(this.player, rank.player)
        && Objects.equals(this.score, rank.score) && Objects.equals(this.time, rank.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.player, this.score, this.time);
  }

  @Override
  public String toString() {
    return "Rank{" + "id=" + this.id + ", player='" + this.player + '\'' + ", score='" + this.score + '\'' + ", time='" + this.time + '\'' + '}';
  }
}