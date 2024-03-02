package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.Team;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TeamRepository;

@RestController
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private TeamRepository teamRepository;

	@PostMapping(path = "/member") // Map ONLY POST Requests
	public @ResponseBody String addNewMember(@RequestParam Integer teamId, @RequestParam String name,
		@RequestParam String password,
		@RequestParam String email) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		password = bCryptPasswordEncoder.encode(password);
		Member n = new Member(name, password, email);
		System.out.println(n.toString());
		n.setTeam(teamRepository.findById(teamId).orElseThrow());
		memberRepository.save(n);
		return "Saved";
	}

	@PostMapping("/login")
	public @ResponseBody String login(@RequestParam String name, @RequestParam String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Member member = memberRepository.findByName(name);
		if (member != null) {
			if (bCryptPasswordEncoder.matches(password, member.getPassword())) {
				System.out.println("Password matched");
				String token = UUID.randomUUID().toString();
				member.setToken(token);
				// member.setPassword(password);
				memberRepository.save(member);
				return token;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@GetMapping(path = "/member")
	public @ResponseBody Iterable<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@GetMapping(path = "/member/{id}")
	public @ResponseBody Member getMember(@PathVariable Integer id) {
		return memberRepository.findById(id).orElseThrow();
	}

	@DeleteMapping(path = "/member/{id}")
	public String delete(@PathVariable Integer id) {
		memberRepository.deleteById(id);
		return "deleted";
	}

	@PutMapping(path = "/member/{id}")
	public String put(@PathVariable Integer id, @RequestParam Integer teamId, @RequestParam String name,
		@RequestParam String email) {
		Member n = memberRepository.findById(id).orElseThrow();
		Team team = teamRepository.findById(teamId).orElseThrow();
		n.setMember(team, name, email);
		memberRepository.save(n);
		return "updated";
	}

}
