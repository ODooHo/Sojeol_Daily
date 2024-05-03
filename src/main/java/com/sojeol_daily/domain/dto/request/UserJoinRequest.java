package com.sojeol_daily.domain.dto.request;

public record UserJoinRequest(
        String userEmail,
        String password,
        String nickname

) {
    public static UserJoinRequest of(String userEmail, String password, String nickname){
        return new UserJoinRequest(userEmail,password,nickname);
    }
}
