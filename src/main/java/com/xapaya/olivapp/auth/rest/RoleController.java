package com.xapaya.olivapp.auth.rest;

import com.xapaya.olivapp.auth.model.Role;
import com.xapaya.olivapp.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @PostMapping("/{name}")
    public ResponseEntity<?> add(@RequestParam String name) {
        if(!roleRepository.findByName(name).isPresent()) {
            roleRepository.save(Role.builder().name(name).build());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@RequestParam String name) {
        roleRepository.findByName(name).ifPresent(roleRepository::delete);
        return ResponseEntity.ok().build();
    }
}
