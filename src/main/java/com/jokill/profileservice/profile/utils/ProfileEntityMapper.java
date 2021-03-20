package com.jokill.profileservice.profile.utils;

import com.jokill.profileservice.profile.application.ProfileDTO;
import com.jokill.profileservice.profile.domain.Profile;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface ProfileEntityMapper {

    ProfileDTO toProfileDTO(Profile profile);
}
