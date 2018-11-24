package com.apis.watson.language;

import com.apis.Config;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiedLanguage;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiedLanguages;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifyOptions;

class Detector {

    /*
     * detect language using the watson SDK
     */
    static IdentifiedLanguage detectLanguages(String text) {
        LanguageTranslator service = new LanguageTranslator("2018-05-01");

//        create a AzureConfig.java file containing your username and password
        service.setUsernameAndPassword(Config.Watson.Language.USERNAME, Config.Watson.Language.PASSWORD);

        IdentifyOptions identifyOptions = new IdentifyOptions.Builder()
                .text(text)
                .build();

        IdentifiedLanguages identifiedLanguages = service.identify(identifyOptions).execute();

        return identifiedLanguages.getLanguages().get(0);
    }

}
