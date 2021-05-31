package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.DeleteProfileService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DeleteProfileController {

    private final DeleteProfileService deleteProfileService;

    @DeleteMapping("/profile/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable("profileId") UUID profileId) throws NotFoundException {
        deleteProfileService.deleteProfile(profileId);
    }
}
