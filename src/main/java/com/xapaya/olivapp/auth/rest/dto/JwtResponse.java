package com.xapaya.olivapp.auth.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class JwtResponse {
    String token;
    String id;
    String username;
    String email;
    List<String> roles;
}
