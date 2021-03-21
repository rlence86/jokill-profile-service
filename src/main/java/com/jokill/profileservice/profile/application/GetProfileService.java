package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.persistance.ProfileRepository;
import com.jokill.profileservice.profile.domain.Profile;
import com.jokill.profileservice.profile.utils.ProfileEntityMapper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileEntityMapper profileEntityMapper;

    public ProfileDTO getProfileById(UUID profileId) throws NotFoundException {
        Optional<Profile> foundProfile = profileRepository.findById(profileId);
        return foundProfile.map(profileEntityMapper::toProfileDTO)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }
}
