package com.jokill.profileservice.profile.application;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProfileDTO {
    private UUID profileId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
