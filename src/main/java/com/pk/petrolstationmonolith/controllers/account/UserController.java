package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.services.account.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

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

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void sendUpdateEmailMail(Principal principal) {
        userService.sendUpdateEmailMail(Long.parseLong(principal.getName()));
    }

    @PutMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmail(@Valid @RequestBody RequestUpdateEmail request, Principal principal) {
        userService.updateEmail(Long.parseLong(principal.getName()), request);
    }

    @DeleteMapping("/users")
    public ResponseEntity<UserDto> disableUser(Principal principal) {
        return ResponseEntity.ok(userService.disableUser(Long.parseLong(principal.getName())));
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<UserDto> disableUserById(@PathVariable long userId) {
        return ResponseEntity.ok(userService.disableUser(userId));
    }



}
