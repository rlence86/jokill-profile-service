package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.GetProfileService;
import com.jokill.profileservice.profile.application.ProfileDTO;
import javassist.NotFoundException;
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

@DisplayName("Get profile controller unit tests")
@ExtendWith(MockitoExtension.class)
class GetProfileControllerTest {

    @InjectMocks
    private GetProfileController controller;

    @Mock
    private GetProfileService getProfileService;

    @Test
    void testGetProfile() throws NotFoundException {
        UUID profileId = UUID.randomUUID();
        givenGetProfileDependenciesAreMocked(profileId);

        ProfileDTO result = whenGetProfileIsCalled(profileId.toString());

        thenGetProfileResultIsExpected(result, givenProfileDTOWithUUID(profileId));
    }

    private void thenGetProfileResultIsExpected(ProfileDTO result, ProfileDTO expected) {
        assertThat(result, is(expected));
    }

    private ProfileDTO whenGetProfileIsCalled(String profileId) throws NotFoundException {
        return controller.getProfile(profileId);
    }

    private void givenGetProfileDependenciesAreMocked(UUID profileId) throws NotFoundException {
        when(getProfileService.getProfileById(profileId)).thenReturn(givenProfileDTOWithUUID(profileId));
    }

    private ProfileDTO givenProfileDTOWithUUID(UUID profileId) {
        return ProfileDTO.builder()
                .profileId(profileId)
                .userName("userName")
                .firstName("Test")
                .lastName("Player")
                .email("myemail@test.com")
                .build();
    }

}