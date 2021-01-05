package com.security.jwt.repository;

import com.security.jwt.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Member save(Member member);
    public Optional<Member> findById(Long id);
    public Optional<Member> findByEmail(String email);
    public List<Member> findAll();
}
