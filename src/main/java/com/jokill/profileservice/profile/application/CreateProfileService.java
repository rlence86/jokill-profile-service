package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.adapter.out.ProfileRepository;
import com.jokill.profileservice.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateProfileService {

    private final ProfileRepository repository;

    private final IdentifierGeneratorService identifierGeneratorService;

    public UUID createProfileAndReturnId(CreateProfileDTO profileData) {
        Profile persistedProfile = repository.save(mapCreateProfileDTOtoProfile(profileData));
        return persistedProfile.getProfileId();
    }

    private Profile mapCreateProfileDTOtoProfile(CreateProfileDTO profileData) {
        return Profile.builder()
                .profileId(identifierGeneratorService.getNewIdentifier())
                .userName(profileData.getUserName())
                .firstName(profileData.getFirstName())
                .lastName(profileData.getLastName())
                .email(profileData.getEmail())
                .build();
    }
}
