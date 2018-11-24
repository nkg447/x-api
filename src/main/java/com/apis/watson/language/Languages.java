package com.apis.watson.language;

import com.apis.Config;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.IdentifiableLanguages;
import com.ibm.watson.developer_cloud.language_translator.v3.model.ListIdentifiableLanguagesOptions;

public class Languages {

    /*
    * Return languages that are identifiable by Watson API
    */
    static IdentifiableLanguages languages() {
        LanguageTranslator service = new LanguageTranslator("2018-05-01");

//        create a Config.java file containing your username and password
        service.setUsernameAndPassword(Config.Watson.Language.USERNAME, Config.Watson.Language.PASSWORD);

        ListIdentifiableLanguagesOptions listOptions = new ListIdentifiableLanguagesOptions.Builder()
                .build();

        IdentifiableLanguages identifiableLanguages = service.listIdentifiableLanguages(listOptions).execute();

        return identifiableLanguages;

    }

}
