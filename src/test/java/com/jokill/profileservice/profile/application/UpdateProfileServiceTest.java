package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.domain.Profile;
import com.jokill.profileservice.profile.persistance.ProfileRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("UpdateProfileService unit tests")
@ExtendWith(MockitoExtension.class)
class UpdateProfileServiceTest {

    private static final UUID PROFILE_ID = UUID.randomUUID();

    @InjectMocks
    UpdateProfileService updateProfileService;

    @Mock
    ProfileRepository repository;

    @Test
    void testUpdateProfile() throws NotFoundException {
        when(repository.findById(PROFILE_ID)).thenReturn(givenFoundProfile());
        updateProfileService.updateProfile(PROFILE_ID, givenCreateProfileDTO());

        verify(repository).findById(PROFILE_ID);
    }

    private Optional<Profile> givenFoundProfile() {
        return Optional.ofNullable(Profile.builder()
                .profileId(PROFILE_ID)
                .email("test@mail.com")
                .firstName("Test")
                .lastName("User")
                .userName("testUser")
                .build());
    }

    private CreateProfileDTO givenCreateProfileDTO() {
        return CreateProfileDTO.builder().build();
    }

}