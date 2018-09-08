package com.apis.google.language;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

class Detector {

    private static Translate translate;

    /*
     * detect language using the watson SDK
     */
    static Detection detectLanguage(String text) throws IOException {

        String classpathLocation="client_secret.json";
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(classpathLocation));


        TranslateOptions translateOptions= TranslateOptions
                .newBuilder()
                .setCredentials(credentials).build();

        translate = translateOptions.getService();

        List<String> texts = new LinkedList<>();
        texts.add(text);
        List<Detection> detections = translate.detect(texts);

        return detections.get(0);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(detectLanguage("Hello World"));
    }

}
