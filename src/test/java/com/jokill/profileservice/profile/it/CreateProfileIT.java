package com.jokill.profileservice.profile.it;

import com.jokill.profileservice.profile.application.CreateProfileDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateProfileIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateProfile() throws Exception {
        CreateProfileDTO createProfileDTO = CreateProfileDTO.builder()
                .userName("userName")
                .firstName("TestName")
                .lastName("TestLastName")
                .email("testmail@gmail.com")
                .build();
        ResponseEntity<UUID> response = testRestTemplate.postForEntity("/profile", createProfileDTO, UUID.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error in profile creation");
        }

    }
}
