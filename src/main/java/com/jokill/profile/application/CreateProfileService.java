package com.jokill.profile.application;

import com.jokill.profile.adapter.out.ProfileRepository;
import com.jokill.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateProfileService {

    private final ProfileRepository repository;

    public UUID createProfileAndReturnId(CreateProfileDTO profileData) {
        Profile persistedProfile = repository.save(mapCreateProfileDTOtoProfile(profileData));
        return persistedProfile.getProfileId();
    }

    private Profile mapCreateProfileDTOtoProfile(CreateProfileDTO profileData) {
        return Profile.builder()
                .firstName(profileData.getFirstName())
                .lastName(profileData.getLastName())
                .dateOfBirth(profileData.getDateOfBirth())
                .build();
    }
}
