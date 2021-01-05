package com.security.jwt.service;

import com.security.jwt.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public Long join(Member member);
    public Boolean validateDuplicateMember(Member member);
    public Boolean login(String email, String password);
    public List<Member> findAllMembers();
    public Optional<Member> findOne(Long id);
}
