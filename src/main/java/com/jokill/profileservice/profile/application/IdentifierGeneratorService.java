package com.jokill.profileservice.profile.application;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdentifierGeneratorService {

    public UUID getNewIdentifier() {
        return UUID.randomUUID();
    }
}
