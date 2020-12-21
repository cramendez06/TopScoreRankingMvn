package com.TopScoreRanking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
class RankingController {

  private final RankingRepository repository;
  
  private final RankingModelAssembler assembler;
  
  private final RankingHistoryModelAssembler assemblerhs;
  
  @Autowired
  RankingService rankService;

  RankingController(RankingRepository repository, RankingModelAssembler assembler, RankingHistoryModelAssembler assemblerhs) {
    this.repository = repository;
    this.assembler = assembler;
    this.assemblerhs = assemblerhs;
  }

  // Aggregate root

  @GetMapping("/ranking/all")
  CollectionModel<EntityModel<Ranking>> all() {

    List<EntityModel<Ranking>> rank = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(rank, linkTo(methodOn(RankingController.class).all()).withSelfRel());
  }

  @PostMapping("/ranking/register")
  ResponseEntity<?> newRanking(@RequestBody Ranking newRanking) {
	  EntityModel<Ranking> entityModel = assembler.toModel(repository.save(newRanking));
    
    return ResponseEntity //
    	      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
    	      .body(entityModel);
  }

  // Single item
  @GetMapping("/ranking/searchById")
  ResponseEntity<?> searchById(@RequestParam(required = true) Long id) {

	  Ranking rank = repository.findById(id) //
        .orElseThrow(() -> new RankingNotFoundException(id));

	  EntityModel<Ranking> entityModel = assembler.toModel(rank);
	  
	  return ResponseEntity //
		        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		        .body(entityModel);
  }
  
  @GetMapping("/ranking/searchByPlayers")
  ResponseEntity<?> searchByPlayers(@RequestParam(required = true) List<String> player, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "")@DateTimeFormat(pattern = "yyyyMMddHHmmss") LocalDateTime time, @RequestParam(defaultValue = "") String direction) {
	  CollectionModel<EntityModel<Ranking>> collectionModel = rankService.filterByPlayersAndDate(repository, assembler, player, page, size, time, direction);
	  
	  return ResponseEntity //
		        .created(collectionModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		        .body(collectionModel);
  }
  
  @GetMapping("/ranking/history")
  ResponseEntity<?> getHistory(@RequestParam(required = true) String player, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "")@DateTimeFormat(pattern = "yyyyMMddHHmmss") LocalDateTime time, @RequestParam(defaultValue = "") String direction) {
	  CollectionModel<EntityModel<RankingHistory>> collectionModel = rankService.getPlayerHistory(repository, assemblerhs, player, page, size, time, direction);
	  
	  return ResponseEntity //
		        .created(collectionModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		        .body(collectionModel);
  }

  @PutMapping("/ranking/update")
  ResponseEntity<?> replaceRanking(@RequestBody Ranking newRanking, @PathVariable Long id) {

	  Ranking updatedRanking =  repository.findById(id)
      .map(rank -> {
    	  rank.setPlayer(newRanking.getPlayer());
    	  rank.setScore(newRanking.getScore());
    	  rank.setTime(newRanking.getTime());
        return repository.save(rank);
      })
      .orElseGet(() -> {
    	  newRanking.setId(id);
        return repository.save(newRanking);
      });
    
    EntityModel<Ranking> entityModel = assembler.toModel(updatedRanking);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  @DeleteMapping("/ranking/delete")
  ResponseEntity<?> deleteRanking(@PathVariable Long id) {
    repository.deleteById(id);
    
    return ResponseEntity.noContent().build();
  }
}