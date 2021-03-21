package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.domain.Profile;
import com.jokill.profileservice.profile.persistance.ProfileRepository;
import com.jokill.profileservice.profile.utils.ProfileEntityMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("GetProfileService unit tests")
@ExtendWith(MockitoExtension.class)
class GetProfileServiceTest {

    private static final UUID UUID_1 = UUID.fromString("2c90591e-8b78-4d52-9abf-e064f926b1a1");
    private static final UUID UUID_2 = UUID.fromString("d15a71af-71d9-46f1-8128-bccc05100ef8");

    @InjectMocks
    GetProfileService getProfileService;

    @Mock
    ProfileRepository repository;

    @Mock
    ProfileEntityMapper mapper;

    @Test
    void testGetProfile() throws NotFoundException {
        UUID profileId = UUID.randomUUID();
        givenDependenciesAreMockedForGetProfileById(profileId);

        ProfileDTO profileDTO = whenGetProfileByIdIsCalled(profileId);

        thenGetProfileResultIsExpected(profileId, profileDTO);
    }

    @Test
    void testGetAllProfiles() {
        List<ProfileDTO> expectedResult = givenProfileDTOList(List.of(UUID_1, UUID_2));
        givenDependenciesAreMockedForGetAllProfiles(List.of(UUID_1, UUID_2));

        List<ProfileDTO> foundProfiles = whenGetAllProfilesIsCalled();
        thenGetAllProfilesResultIsExpected(foundProfiles, expectedResult);
    }

    private void givenDependenciesAreMockedForGetProfileById(UUID profileId) {
        when(repository.findById(profileId)).thenReturn(Optional.of(givenProfileWithUUID(profileId)));
        when(mapper.toProfileDTO(givenProfileWithUUID(profileId))).thenReturn(givenProfileDTOWithUUID(profileId));
    }

    private void givenDependenciesAreMockedForGetAllProfiles(List<UUID> uuids) {
        when(repository.findAll()).thenReturn(givenProfileList(uuids));
        when(mapper.toProfileDTO(givenProfileWithUUID(UUID_1))).thenReturn(givenProfileDTOWithUUID(UUID_1));
        when(mapper.toProfileDTO(givenProfileWithUUID(UUID_2))).thenReturn(givenProfileDTOWithUUID(UUID_2));
    }

    private ProfileDTO whenGetProfileByIdIsCalled(UUID profileId) throws NotFoundException {
        return getProfileService.getProfileById(profileId);
    }

    private List<ProfileDTO> whenGetAllProfilesIsCalled() {
        return getProfileService.getAllProfiles();
    }

    private void thenGetProfileResultIsExpected(UUID profileId, ProfileDTO profileDTO) {
        assertThat(profileDTO, is(givenProfileDTOWithUUID(profileId)));
    }

    private void thenGetAllProfilesResultIsExpected(List<ProfileDTO> foundProfiles, List<ProfileDTO> expectedResult) {
        assertThat(foundProfiles, is(expectedResult));
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

    private List<Profile> givenProfileList(List<UUID> uuids) {
        return uuids.stream().map(this::givenProfileWithUUID).collect(Collectors.toList());
    }

    private List<ProfileDTO> givenProfileDTOList(List<UUID> uuids) {
        return uuids.stream().map(this::givenProfileDTOWithUUID).collect(Collectors.toList());
    }
}