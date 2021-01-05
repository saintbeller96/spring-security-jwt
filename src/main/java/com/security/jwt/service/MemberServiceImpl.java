package com.security.jwt.service;

import com.security.jwt.domain.Member;
import com.security.jwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long join(Member member) {
        if(!validateDuplicateMember(member)) return 0L;
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Boolean validateDuplicateMember(Member member) {
        AtomicBoolean result = new AtomicBoolean(true);
        memberRepository.findByEmail(member.getEmail()).ifPresent(m->{
            result.set(false);
        });
        return result.get();
    }

    @Override
    public Boolean login(String email, String password) {
        return null;
    }

    @Override
    public List<Member> findAllMembers() {
        return null;
    }

    @Override
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
