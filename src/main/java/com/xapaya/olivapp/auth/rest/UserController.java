package com.xapaya.olivapp.auth.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess(UsernamePasswordAuthenticationToken userDetails) {
        log.info("User: {}", userDetails.getDetails());
        log.info("Context: {}", SecurityContextHolder.getContext().getAuthentication());
        return "User Content. " + userDetails.getName();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String userAccessAdmin(UsernamePasswordAuthenticationToken userDetails) {
        log.info("User: {}", userDetails);
        return "User Content." + userDetails.getName();
    }
}
