package com.jokill.profile.domain;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@With
public class Profile {
    private UUID profileId;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
