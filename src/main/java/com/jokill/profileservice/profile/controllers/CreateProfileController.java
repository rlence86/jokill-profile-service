package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.CreateProfileDTO;
import com.jokill.profileservice.profile.application.CreateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CreateProfileController {

    private final CreateProfileService createProfileService;

    @PostMapping("/profile")
    public UUID createProfile(@RequestBody CreateProfileDTO createProfileDTO) {
        return createProfileService.createProfileAndReturnId(createProfileDTO);
    }

}
