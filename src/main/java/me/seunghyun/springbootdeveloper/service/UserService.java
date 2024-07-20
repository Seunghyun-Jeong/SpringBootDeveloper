package me.seunghyun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.seunghyun.springbootdeveloper.domain.User;
import me.seunghyun.springbootdeveloper.dto.AddUserRequest;
import me.seunghyun.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
