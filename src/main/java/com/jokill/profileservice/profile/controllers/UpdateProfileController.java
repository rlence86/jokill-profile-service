package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.CreateProfileDTO;
import com.jokill.profileservice.profile.application.UpdateProfileService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UpdateProfileController {

    private final UpdateProfileService updateProfileService;

    @PutMapping("/profile/{profileId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProfile(@PathVariable("profileId") UUID profileId, @RequestBody CreateProfileDTO createProfileDTO) throws NotFoundException {
        updateProfileService.updateProfile(profileId, createProfileDTO);
    }
}
