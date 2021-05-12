package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.UpdateProfileService;
import com.jokill.profileservice.profile.application.CreateProfileDTO;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@DisplayName("Update profile controller unit tests")
@ExtendWith(MockitoExtension.class)
class UpdateProfileControllerTest {

    private static final UUID PROFILE_ID = UUID.randomUUID();

    @InjectMocks
    UpdateProfileController updateProfileController;

    @Mock
    UpdateProfileService updateProfileService;

    @Test
    void testUpdateProfile() throws NotFoundException {
        doNothing().when(updateProfileService).updateProfile(PROFILE_ID, givenCreateProfileDTO());
        updateProfileController.updateProfile(PROFILE_ID, givenCreateProfileDTO());

        verify(updateProfileService).updateProfile(PROFILE_ID, givenCreateProfileDTO());
    }

    private CreateProfileDTO givenCreateProfileDTO() {
        return CreateProfileDTO.builder().build();
    }
}