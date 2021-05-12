package com.jokill.profileservice.profile.it;

import com.jokill.profileservice.profile.application.CreateProfileDTO;
import com.jokill.profileservice.profile.application.ProfileDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileCrudIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateProfile() {
        CreateProfileDTO createProfileDTO = CreateProfileDTO.builder()
                .userName("userName")
                .firstName("TestName")
                .lastName("TestLastName")
                .email("testmail@gmail.com")
                .build();
        ResponseEntity<UUID> response = testRestTemplate.postForEntity("/profile", createProfileDTO, UUID.class);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Returned code is not correct");
        }

        String createdProfileId = response.getBody().toString();

        ResponseEntity<ProfileDTO> foundProfile = testRestTemplate
                .getForEntity("/profile/"+createdProfileId, ProfileDTO.class);
        if (!foundProfile.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error finding profile");
        }

        assertThat(foundProfile.getBody().getUserName(), is("userName"));

        CreateProfileDTO updateProfileDTO = CreateProfileDTO.builder()
                .userName("userName2")
                .firstName("TestName2")
                .lastName("TestLastName2")
                .email("testmail2@gmail.com")
                .build();

        ResponseEntity<Void> updatedResponse = testRestTemplate
                .put("/profile/"+createdProfileId, updateProfileDTO);

        if (updatedResponse.getStatusCode() != HttpStatus.ACCEPTED) {
            throw new RuntimeException("Error finding profile");
        }
    }
}
