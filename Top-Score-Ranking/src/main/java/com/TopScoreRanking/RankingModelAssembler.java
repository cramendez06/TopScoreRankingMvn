package com.TopScoreRanking;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class RankingModelAssembler implements RepresentationModelAssembler<Ranking, EntityModel<Ranking>> {

  @Override
  public EntityModel<Ranking> toModel(Ranking rank) {

    return EntityModel.of(rank, //
        linkTo(methodOn(RankingController.class).searchById(rank.getId())).withSelfRel(),
        linkTo(methodOn(RankingController.class).all()).withRel("all"));
  }
}
