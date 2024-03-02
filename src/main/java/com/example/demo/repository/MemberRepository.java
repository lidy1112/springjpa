package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

	Member findByName(String name);
}
