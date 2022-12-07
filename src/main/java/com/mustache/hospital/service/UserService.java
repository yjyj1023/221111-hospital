package com.mustache.hospital.service;


import com.mustache.hospital.domain.User;
import com.mustache.hospital.exception.AppException;
import com.mustache.hospital.exception.ErrorCode;
import com.mustache.hospital.repository.UserRepository;
import com.mustache.hospital.utils.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60l;

    public String join(String userName, String password){

        //userName 중복 체크
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, userName + "는 이미 있습니다.");
                });

        // 저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String userName, String password){

        //userName 없음 검사
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUNDED, userName+"이 없습니다."));

        //password 틀림 검사
        if(!encoder.matches(password, selectedUser.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
        }

        //토큰 발행
        String token = JwtUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);


        return token;
    }
}