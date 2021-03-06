package com.jokill.profile.application;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateProfileDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
