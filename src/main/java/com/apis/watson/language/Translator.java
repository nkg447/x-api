package com.apis.watson.language;

import com.apis.Config;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.xapi.language.TranslationRequestBody;

public class Translator {

    /*
    * Translating text using the Watson API
     */
    static TranslationResult translate(TranslationRequestBody requestBody) {
        LanguageTranslator service = new LanguageTranslator();

//        create a Config.java file containing your username and password
        service.setUsernameAndPassword(Config.Watson.Language.USERNAME, Config.Watson.Language.PASSWORD);

        TranslateOptions translateOptions = new TranslateOptions.Builder()
                .addText(requestBody.getText())
                .source(requestBody.getSourceLanguage())
                .target(requestBody.getTargetLanguage())
                .build();
        TranslationResult translationResult = service.translate(translateOptions).execute();

        return translationResult;
    }
}
