package com.example.springjpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springjpa.model.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {

}
