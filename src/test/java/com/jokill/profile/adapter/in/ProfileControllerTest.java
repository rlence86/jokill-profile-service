package com.jokill.profile.adapter.in;

import com.jokill.profile.application.CreateProfileDTO;
import com.jokill.profile.application.CreateProfileService;
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

@DisplayName("ProfileController unit tests")
@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private CreateProfileService createProfileService;

    @Test
    void testCreateProfile() {
        CreateProfileDTO createProfileDTO = CreateProfileDTO.builder().build();
        UUID expectedUUID = UUID.randomUUID();
        givenAllDependenciesAreMocked(createProfileDTO, expectedUUID);

        UUID result = whenCreateProfileIsCalled(createProfileDTO);

        thenServiceIsCalledAndResultIsExpected(createProfileDTO, expectedUUID, result);
    }

    private void givenAllDependenciesAreMocked(CreateProfileDTO createProfileDTO, UUID expectedUUID) {
        when(createProfileService.createProfileAndReturnId(createProfileDTO)).thenReturn(expectedUUID);
    }

    private UUID whenCreateProfileIsCalled(CreateProfileDTO createProfileDTO) {
        return profileController.createProfile(createProfileDTO);
    }

    private void thenServiceIsCalledAndResultIsExpected(CreateProfileDTO createProfileDTO, UUID expectedUUID, UUID result) {
        assertThat(result, is(expectedUUID));
        verify(createProfileService).createProfileAndReturnId(createProfileDTO);
    }

}