package com.jokill.profileservice.profile.adapter.in;

import com.jokill.profileservice.profile.application.CreateProfileDTO;
import com.jokill.profileservice.profile.application.CreateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final CreateProfileService createProfileService;

    @PostMapping
    public UUID createProfile(@RequestBody CreateProfileDTO createProfileDTO) {
        return createProfileService.createProfileAndReturnId(createProfileDTO);
    }
}
