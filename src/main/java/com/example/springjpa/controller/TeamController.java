package com.example.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.model.Team;
import com.example.springjpa.repository.TeamRepository;

/**
 * 자바독 추가
 */
@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@PostMapping(path = "/team")
	public @ResponseBody String post(@RequestParam String name) {
		Team team = new Team(name);
		teamRepository.save(team);
		return "Saved";
	}

	@GetMapping(path = "/team")
	public @ResponseBody Iterable<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@GetMapping(path = "/team/{id}")
	public @ResponseBody Team getTeam(@PathVariable Integer id) {
		return teamRepository.findById(id).orElseThrow();
	}

	@DeleteMapping(path = "/team/{id}")
	public String delete(@PathVariable Integer id) {
		teamRepository.deleteById(id);
		return "deleted";
	}

	@PutMapping(path = "/team/{id}")
	public String put(@PathVariable Integer id, @RequestParam String name) {
		Team team = teamRepository.findById(id).orElseThrow();
		team.setName(name);
		teamRepository.save(team);
		return "updated";
	}
}
