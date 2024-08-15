package com.bluestarfish.blueberry.user.service;

import com.bluestarfish.blueberry.user.dto.JoinRequest;
import com.bluestarfish.blueberry.user.dto.UserUpdateRequest;
import com.bluestarfish.blueberry.user.dto.UserResponse;

public interface UserService {
    void join(JoinRequest joinRequest);
    UserResponse findById(Long id);
    void update(Long id, UserUpdateRequest userUpdateRequest);
    void withdraw(Long id);
    void validateNickname(String nickname);
}
