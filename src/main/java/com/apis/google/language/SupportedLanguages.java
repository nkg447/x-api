package com.apis.google.language;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

import java.io.IOException;
import java.util.List;

public class SupportedLanguages {

    static List<Language> getSupportedLanguages() throws IOException {
        TranslateOptions translateOptions= TranslateOptions
                .newBuilder()
                .setCredentials(com.apis.google.Credential.getCredential()).build();

        Translate translate = translateOptions.getService();

        List<Language> languages = translate.listSupportedLanguages();
        return languages;
    }
}
