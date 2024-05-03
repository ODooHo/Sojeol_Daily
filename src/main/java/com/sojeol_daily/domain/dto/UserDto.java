package com.sojeol_daily.domain.dto;

import com.sojeol_daily.domain.entity.UserEntity;

import java.sql.Timestamp;

public record UserDto(
        Integer id, String userEmail, String password, String nickname, Timestamp registeredAt,
        Timestamp updatedAt, Timestamp removedAt
) {

    public static UserDto from(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getUserEmail(), entity.getPassword(), entity.getNickname(), entity.getRegisteredAt(), entity.getUpdatedAt(), entity.getRemovedAt());
    }

    public UserEntity toEntity() {
        return UserEntity.of(userEmail, password, nickname);
    }

    public static UserDto of(Integer id, String userEmail, String password, String nickname, Timestamp registeredAt, Timestamp updatedAt, Timestamp removedAt) {
        return new UserDto(id, userEmail, password, nickname, registeredAt, updatedAt, removedAt);
    }


}
