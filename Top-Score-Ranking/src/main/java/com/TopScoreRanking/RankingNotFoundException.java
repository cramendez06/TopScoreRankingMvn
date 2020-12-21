package com.TopScoreRanking;

class RankingNotFoundException extends RuntimeException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

RankingNotFoundException(Long id) {
    super("Could not find rank " + id);
  }
}
