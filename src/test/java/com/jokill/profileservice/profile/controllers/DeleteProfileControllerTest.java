package com.jokill.profileservice.profile.controllers;

import com.jokill.profileservice.profile.application.DeleteProfileService;
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

@DisplayName("Delete profile controller unit tests")
@ExtendWith(MockitoExtension.class)
class DeleteProfileControllerTest {

    private static final UUID PROFILE_ID = UUID.randomUUID();

    @InjectMocks
    DeleteProfileController deleteProfileController;

    @Mock
    DeleteProfileService deleteProfileService;

    @Test
    void testDeleteProfile() throws NotFoundException {
        doNothing().when(deleteProfileService).deleteProfile(PROFILE_ID);
        deleteProfileController.deleteProfile(PROFILE_ID);

        verify(deleteProfileService).deleteProfile(PROFILE_ID);
    }
}