package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.persistance.ProfileRepository;
import com.jokill.profileservice.profile.domain.Profile;
import com.jokill.profileservice.profile.utils.ProfileEntityMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("GetProfileService unit tests")
@ExtendWith(MockitoExtension.class)
class GetProfileServiceTest {

    @InjectMocks
    GetProfileService getProfileService;

    @Mock
    ProfileRepository repository;

    @Mock
    ProfileEntityMapper mapper;

    @Test
    void testGetProfile() throws NotFoundException {
        UUID profileId = UUID.randomUUID();
        givenDependenciesAreMocked(profileId);

        ProfileDTO profileDTO = whenGetProfileByIdIsCalled(profileId);

        thenResultIsExpected(profileId, profileDTO);
    }

    private void givenDependenciesAreMocked(UUID profileId) {
        when(repository.findById(profileId)).thenReturn(Optional.of(givenProfileWithUUID(profileId)));
        when(mapper.toProfileDTO(givenProfileWithUUID(profileId))).thenReturn(givenProfileDTOWithUUID(profileId));
    }

    private ProfileDTO whenGetProfileByIdIsCalled(UUID profileId) throws NotFoundException {
        return getProfileService.getProfileById(profileId);
    }

    private void thenResultIsExpected(UUID profileId, ProfileDTO profileDTO) {
        assertThat(profileDTO, is(givenProfileDTOWithUUID(profileId)));
    }

    private Profile givenProfileWithUUID(UUID profileId) {
        return Profile.builder()
                .profileId(profileId)
                .userName("userName")
                .firstName("Test")
                .lastName("Player")
                .email("myemail@test.com")
                .build();
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