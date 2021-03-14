package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.adapter.out.ProfileRepository;
import com.jokill.profileservice.profile.domain.Profile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CreateProfileService unit tests")
@ExtendWith(MockitoExtension.class)
class CreateProfileServiceTest {

    @InjectMocks
    private CreateProfileService service;

    @Mock
    private ProfileRepository repository;

    @Test
    void testCreateProfile() {
        CreateProfileDTO createProfileDTO = givenCreateProfileDTO();
        Profile profileToSave = givenProfileToSave();
        UUID expectedUUID = UUID.randomUUID();
        givenDependenciesAreMocked(profileToSave, expectedUUID);

        UUID createdUserId = whenCreateProfileAndReturnIDIsCalled(createProfileDTO);

        thenRepositorySaveIsCalledAndResultIsExpected(profileToSave, expectedUUID, createdUserId);
    }

    private void givenDependenciesAreMocked(Profile profileToSave, UUID expectedUUID) {
        when(repository.save(profileToSave)).thenReturn(profileToSave.withProfileId(expectedUUID));
    }

    private Profile givenProfileToSave() {
        return Profile.builder()
                .firstName("TestName")
                .lastName("TestLastname")
                .email("TestEmail")
                .build();
    }

    private CreateProfileDTO givenCreateProfileDTO() {
        return CreateProfileDTO.builder()
                .firstName("TestName")
                .lastName("TestLastname")
                .email("TestEmail")
                .build();
    }

    private UUID whenCreateProfileAndReturnIDIsCalled(CreateProfileDTO createProfileDTO) {
        return service.createProfileAndReturnId(createProfileDTO);
    }

    private void thenRepositorySaveIsCalledAndResultIsExpected(Profile profileToSave, UUID expectedUUID, UUID createdUserId) {
        assertThat(createdUserId, is(expectedUUID));
        verify(repository).save(profileToSave);
    }
}