package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.persistance.ProfileRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProfileService {

    private final ProfileRepository profileRepository;

    public void deleteProfile(UUID profileId) throws NotFoundException {
        try {
            profileRepository.deleteById(profileId);
        } catch (RuntimeException ex) {
            throw new NotFoundException("Couldn't remove element with UUID "+ profileId.toString());
        }
    }
}
