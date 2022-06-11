package com.xapaya.olivapp.jobs.service;

import com.xapaya.olivapp.auth.service.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static String getCurrentUserId() {
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
