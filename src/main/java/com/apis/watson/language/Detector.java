package com.apis.watson.language;

import com.apis.watson.Config;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiedLanguage;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiedLanguages;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifyOptions;

public class Detector {

    static IdentifiedLanguage detectLanguages(String text) {
        LanguageTranslator service = new LanguageTranslator();

//        create a Config.java file containing your username and password
        service.setUsernameAndPassword(Config.Language.USERNAME, Config.Language.PASSWORD);

        IdentifyOptions identifyOptions = new IdentifyOptions.Builder()
                .text(text)
                .build();

        IdentifiedLanguages identifiedLanguages = service.identify(identifyOptions).execute();

        return identifiedLanguages.getLanguages().get(0);
    }

}
