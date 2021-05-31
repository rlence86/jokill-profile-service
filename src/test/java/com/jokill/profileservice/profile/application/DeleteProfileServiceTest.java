package com.jokill.profileservice.profile.application;

import com.jokill.profileservice.profile.persistance.ProfileRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@DisplayName("DeleteProfileService unit tests")
@ExtendWith(MockitoExtension.class)
class DeleteProfileServiceTest {

    private static final UUID PROFILE_ID = UUID.randomUUID();

    @InjectMocks
    DeleteProfileService deleteProfileService;

    @Mock
    ProfileRepository repository;

    @Test
    void testDeleteProfile() throws NotFoundException {
        doNothing().when(repository).deleteById(PROFILE_ID);
        deleteProfileService.deleteProfile(PROFILE_ID);

        verify(repository).deleteById(PROFILE_ID);
    }

    @Test
    void testDeleteProfileWhenDeleteThrowsExceptionThenThrowNotFoundException() {
        doThrow(new EmptyResultDataAccessException("Test", 1)).when(repository).deleteById(PROFILE_ID);

        assertThrows(NotFoundException.class, () -> deleteProfileService.deleteProfile(PROFILE_ID));
    }
}