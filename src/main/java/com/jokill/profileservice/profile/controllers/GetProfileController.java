package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.GetProfileService;
import com.jokill.profileservice.profile.application.ProfileDTO;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GetProfileController {

    private final GetProfileService getProfileService;

    @GetMapping("/profile/{profileId}")
    public ProfileDTO getProfile(@PathVariable String profileId) throws NotFoundException {
        UUID profileUuid = UUID.fromString(profileId);
        return getProfileService.getProfileById(profileUuid);
    }

    @GetMapping("/profile")
    public List<ProfileDTO> getAllProfiles() {
        return getProfileService.getAllProfiles();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
