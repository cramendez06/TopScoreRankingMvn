package com.TopScoreRanking;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class RankingService {
	
	public CollectionModel<EntityModel<Ranking>> filterByPlayersAndDate(RankingRepository repository, RankingModelAssembler assembler, List<String> player, int page, int size, LocalDateTime time, String direction) {
		  Pageable paging = PageRequest.of(page, size);
		  Page<Ranking> pageDate;
		  
		  if (direction.equals("before")) {
			  pageDate = repository.findByPlayerInAndTimeBefore(player, time, paging);
		  } else if (direction.equals("after")) {
			  pageDate = repository.findByPlayerInAndTimeAfter(player, time, paging);
		  } else {
			  pageDate = repository.findByPlayerIn(player, paging);
		  }
		  
		  int totalPage = pageDate.getTotalPages();
		  int nextPage = Math.min(page + 1, totalPage - 1);
		  int prevPage = Math.max(page - 1, 0);
		 
		  List<EntityModel<Ranking>> rank = pageDate.stream()
			      .map(assembler::toModel)
			      .collect(Collectors.toList());
		  
		  return  CollectionModel.of(rank, linkTo(methodOn(RankingController.class)
				  .searchByPlayers(player, page, size, time, direction)).withSelfRel(),
			        linkTo(methodOn(RankingController.class).searchByPlayers(player, nextPage, size, time, direction)).withRel("next"),
			        linkTo(methodOn(RankingController.class).searchByPlayers(player, prevPage, size, time, direction)).withRel("previous"),
			        linkTo(methodOn(RankingController.class).all()).withRel("all"));
	}
	
	public CollectionModel<EntityModel<RankingHistory>> getPlayerHistory(RankingRepository repository, RankingHistoryModelAssembler assembler, String player, int page, int size, LocalDateTime time, String direction) {
		  Pageable paging = PageRequest.of(page, size);
		  Page<RankingHistory> pageDate;
		  
		  pageDate = repository.getPlayer(player, paging);
		  
//		  if (direction.equals("before")) {
//			  pageDate = repository.findByPlayerInAndTimeBefore(player, time, paging);
//		  } else if (direction.equals("after")) {
//			  pageDate = repository.findByPlayerInAndTimeAfter(player, time, paging);
//		  } else {
//			  pageDate = repository.findByPlayerIn(player, paging);
//		  }
		  
		  int totalPage = pageDate.getTotalPages();
		  int nextPage = Math.min(page + 1, totalPage - 1);
		  int prevPage = Math.max(page - 1, 0);
		 
		  List<EntityModel<RankingHistory>> rank = pageDate.stream()
			      .map(assembler::toModel)
			      .collect(Collectors.toList());
		  
		  return  CollectionModel.of(rank, linkTo(methodOn(RankingController.class)
				  .getHistory(player, page, size, time, direction)).withSelfRel(),
			        linkTo(methodOn(RankingController.class).getHistory(player, nextPage, size, time, direction)).withRel("next"),
			        linkTo(methodOn(RankingController.class).getHistory(player, prevPage, size, time, direction)).withRel("previous"),
			        linkTo(methodOn(RankingController.class).all()).withRel("all"));
	}
	
//	public List<RankingHistory> getAllWithMapResult(RankingRepository repository, String player, LocalDateTime time, String direction) {
//        List<Map<String, Object>> results = repository.fetchByPlayerWithMapResult(player);
//        return results.stream().map(result -> new RankingHistory(result))
//		.collect(Collectors.toList());
//    }
}
