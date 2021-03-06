package com.jokill.profile.adapter.in;

import com.jokill.profile.application.CreateProfileDTO;
import com.jokill.profile.application.CreateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("profile")
@RequiredArgsConstructor
public class ProfileController {

    private final CreateProfileService createProfileService;

    @PostMapping
    public UUID createProfile(CreateProfileDTO createProfileDTO) {
        return createProfileService.createProfileAndReturnId(createProfileDTO);
    }
}
