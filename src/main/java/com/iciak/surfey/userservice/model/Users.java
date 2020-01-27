package com.iciak.surfey.userservice.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //STATIC
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Users {
    @NonNull
    List<User> users;
}