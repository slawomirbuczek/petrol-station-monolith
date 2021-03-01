package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestResetPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.services.account.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/password")
@AllArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PutMapping
    public ResponseEntity<ResponseMessage> updatePassword(@Valid @RequestBody RequestUpdatePassword request,
                                                          Principal principal) {
        return ResponseEntity.ok(passwordService.updatePassword(request, Long.parseLong(principal.getName())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessage> sendResetPasswordMail(@Valid @RequestBody RequestResetPassword request) {
        return ResponseEntity.ok(passwordService.sendPasswordResetMail(request));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> setNewPassword(@Valid @RequestBody RequestNewPassword request) {
        return ResponseEntity.ok(passwordService.setNewPassword(request));
    }

}
