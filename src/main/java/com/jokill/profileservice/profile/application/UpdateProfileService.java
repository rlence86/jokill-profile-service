package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.domain.Profile;
import com.jokill.profileservice.profile.persistance.ProfileRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public void updateProfile(UUID profileId, CreateProfileDTO createProfileDTO) throws NotFoundException {
        Profile foundProfile = profileRepository.findById(profileId).orElseThrow(() -> new NotFoundException("Profile not found"));
        updateFoundProfile(foundProfile, createProfileDTO);
    }

    private void updateFoundProfile(Profile profile, CreateProfileDTO createProfileDTO) {
        profile.setEmail(createProfileDTO.getEmail());
        profile.setFirstName(createProfileDTO.getFirstName());
        profile.setLastName(createProfileDTO.getLastName());
        profile.setUserName(createProfileDTO.getUserName());
    }
}
