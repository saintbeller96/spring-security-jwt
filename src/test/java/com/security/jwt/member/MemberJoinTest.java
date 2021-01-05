package com.security.jwt.member;

import com.security.jwt.domain.Member;
import com.security.jwt.repository.MemberRepository;
import com.security.jwt.repository.MemoryMemberRepository;
import com.security.jwt.service.MemberService;
import com.security.jwt.service.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemberJoinTest {

    MemberService memberService;
    MemberRepository memberRepository;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        passwordEncoder = new BCryptPasswordEncoder();
        memberService = new MemberServiceImpl(memberRepository, passwordEncoder);
    }

    @Test
    void join() {
        Member member = new Member();
        member.setEmail("aaaaa@aaa.com");
        member.setPassword("1");

        Long result = memberService.join(member);

        assertThat(result).isSameAs(1L);
    }

    @Test
    void checkDuplicatedEmail() {
        Member member = new Member();
        member.setEmail("aaaaa@aaa.com");
        member.setPassword("1");
        Long result = memberService.join(member);

        Member member2 = new Member();
        member2.setEmail("aaaaa@aaa.com");
        member2.setPassword("1");
        Long result2 = memberService.join(member2);

        assertThat(result2).isSameAs(0L);
    }

    @Test
    void checkEncryptedPwd() {
        Member member = new Member();
        member.setEmail("aaaaa@aaa.com");
        member.setPassword("1");

        Long id = memberService.join(member);
        Member member1 = memberService.findOne(id).get();

        assertThat(member1.getPassword()).isEqualTo(member.getPassword());
    }
}
