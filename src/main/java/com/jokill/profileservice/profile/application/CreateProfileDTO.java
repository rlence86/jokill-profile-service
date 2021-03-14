package com.jokill.profileservice.profile.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
