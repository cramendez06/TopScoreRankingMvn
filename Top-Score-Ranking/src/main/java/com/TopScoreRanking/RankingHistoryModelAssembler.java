package com.TopScoreRanking;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class RankingHistoryModelAssembler implements RepresentationModelAssembler<RankingHistory, EntityModel<RankingHistory>>  {
	@Override
	  public EntityModel<RankingHistory> toModel(RankingHistory rankHistory) {

	    return EntityModel.of(rankHistory, //
	        linkTo(methodOn(RankingController.class)).withSelfRel(),
	        linkTo(methodOn(RankingController.class).all()).withRel("all"));
	  }
}
