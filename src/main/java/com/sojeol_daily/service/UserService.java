package com.sojeol_daily.service;

import com.sojeol_daily.domain.dto.UserDto;
import com.sojeol_daily.domain.entity.UserEntity;
import com.sojeol_daily.exception.CustomApplicationException;
import com.sojeol_daily.exception.ErrorCode;
import com.sojeol_daily.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserDto join(String userEmail, String password, String nickname){
        userRepository.findByUserEmail(userEmail).ifPresent(
                it-> {
                 throw new CustomApplicationException(ErrorCode.DUPLICATED_USER_EMAIL,String.format("user email is %s",userEmail));
                }
        );
        UserEntity userEntity = userRepository.save(UserEntity.of(userEmail, passwordEncoder.encode(password), nickname));
        return UserDto.from(userEntity);
    }

}
