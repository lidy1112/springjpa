package com.example.springjpa;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springjpa.model.Team;
import com.example.springjpa.repository.TeamRepository;

@SpringBootTest
class SpringJPAApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringJPAApplicationTests.class);
	@Autowired
	private TeamRepository teamRepository;

	@Test
	void contextLoads() {
		Team team = teamRepository.findById(1).orElseThrow();
		LOGGER.info("team : " + team);
		//		Iterable<User> users = team.getUsers();
		//		LOGGER.info("user:" + users);
		/*
		 * for (User user : users) { LOGGER.info(user.getId() + " " + user.getName()); }
		 */
	}

}
