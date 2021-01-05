package com.security.jwt.member;

import com.security.jwt.util.JwtUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class CreateJwtTest {
    @Test
    void createJwt() {
        JwtUtil jwtUtil = new JwtUtil();
        UserDetails userDetails = new User("user", "pwd", new ArrayList<>());
        String jwt = jwtUtil.generateToken(userDetails);
        Assertions.assertThat(jwtUtil.extractUsername(jwt)).isEqualTo("user");
    }
}
