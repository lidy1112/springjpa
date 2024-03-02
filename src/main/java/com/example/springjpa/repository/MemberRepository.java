package com.example.springjpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springjpa.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

	Member findByName(String name);
}
