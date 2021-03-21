package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.CreateProfileDTO;
import com.jokill.profileservice.profile.application.CreateProfileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Create profile controller unit tests")
@ExtendWith(MockitoExtension.class)
class CreateProfileControllerTest {

    @InjectMocks
    private CreateProfileController controller;

    @Mock
    private CreateProfileService createProfileService;

    @Test
    void testCreateProfile() {
        CreateProfileDTO createProfileDTO = CreateProfileDTO.builder().build();
        UUID expectedUUID = UUID.randomUUID();
        givenCreateProfileDependenciesAreMocked(createProfileDTO, expectedUUID);

        UUID result = whenCreateProfileIsCalled(createProfileDTO);

        thenCreateProfileResultIsExpected(result, expectedUUID);
    }

    private void givenCreateProfileDependenciesAreMocked(CreateProfileDTO createProfileDTO, UUID expectedUUID) {
        when(createProfileService.createProfileAndReturnId(createProfileDTO)).thenReturn(expectedUUID);
    }

    private UUID whenCreateProfileIsCalled(CreateProfileDTO createProfileDTO) {
        return controller.createProfile(createProfileDTO);
    }

    private void thenCreateProfileResultIsExpected(UUID result, UUID expected) {
        assertThat(result, is(expected));
    }

}