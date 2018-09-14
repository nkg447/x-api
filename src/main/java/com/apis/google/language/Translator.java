package com.apis.google.language;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.xapi.language.TranslationRequestBody;

import java.io.IOException;

class Translator {
    /*
     * Translating text using the Watson API
     */
    static Translation translate(TranslationRequestBody requestBody) throws IOException {
        TranslateOptions translateOptions = TranslateOptions
                .newBuilder()
                .setCredentials(com.apis.google.Credential.getCredential()).build();

        TranslateOption srcLang = TranslateOption.sourceLanguage(requestBody.getSourceLanguage());
        TranslateOption tgtLang = TranslateOption.targetLanguage(requestBody.getTargetLanguage());

        // Use translate `model` parameter with `base` and `nmt` options.
        TranslateOption model = TranslateOption.model("nmt");

        Translate translate = translateOptions.getService();
        Translation translation = translate.translate(requestBody.getText(), srcLang, tgtLang, model);

        return translation;

    }

}
