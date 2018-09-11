package com.apis.google.language;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Detector {

    private static Translate translate;

    /*
     * detect language using the watson SDK
     */
    static Detection detectLanguage(String text) throws IOException {
        TranslateOptions translateOptions= TranslateOptions
                .newBuilder()
                .setCredentials(com.apis.google.Credential.getCredential()).build();

        translate = translateOptions.getService();

        List<String> texts = new LinkedList<>();
        texts.add(text);
        List<Detection> detections = translate.detect(texts);

        return detections.get(0);
    }

}
