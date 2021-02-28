package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.services.account.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody UserCredentials credentials) {
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<UserDto> getUserDtoByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDto(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<UserDto> getUserDto(Principal principal) {
        return ResponseEntity.ok(userService.getUserDto(Long.parseLong(principal.getName())));
    }


}
