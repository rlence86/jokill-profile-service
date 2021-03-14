package com.jokill.profileservice.profile.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@With
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @Column(length = 36, unique = true, nullable = false)
    private UUID profileId;

    @Column
    private String userName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;
}
