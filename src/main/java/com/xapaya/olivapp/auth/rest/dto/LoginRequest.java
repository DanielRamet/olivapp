package com.xapaya.olivapp.auth.rest.dto;

import lombok.Value;

@Value
public class LoginRequest {
    String username;
    String password;
}
