package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.adapter.out.ProfileRepository;
import com.jokill.profileservice.profile.domain.Profile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CreateProfileService unit tests")
@ExtendWith(MockitoExtension.class)
class CreateProfileServiceTest {

    private final UUID PROFILE_ID = UUID.fromString("25772d01-a4b9-4568-9c10-e5f0bc9dab93");

    @InjectMocks
    private CreateProfileService service;

    @Mock
    private ProfileRepository repository;

    @Mock
    private IdentifierGeneratorService identifierGeneratorService;

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
        when(identifierGeneratorService.getNewIdentifier()).thenReturn(PROFILE_ID);
    }

    private Profile givenProfileToSave() {
        return Profile.builder()
                .profileId(PROFILE_ID)
                .userName("userName")
                .firstName("TestName")
                .lastName("TestLastname")
                .email("TestEmail")
                .build();
    }

    private CreateProfileDTO givenCreateProfileDTO() {
        return CreateProfileDTO.builder()
                .userName("userName")
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