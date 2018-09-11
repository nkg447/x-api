package com.apis.google;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;

public class Credential {
    public static GoogleCredentials getCredential() throws IOException {
        String classpathLocation="client_secret.json";
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(classpathLocation));

        return credentials;
    }
}
