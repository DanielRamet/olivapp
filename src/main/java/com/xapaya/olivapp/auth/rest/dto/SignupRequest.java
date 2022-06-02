package com.xapaya.olivapp.auth.rest.dto;

import lombok.Value;

import java.util.List;

@Value
public class SignupRequest {
    String username;
    String email;
    String password;
    List<String> roles;
}
