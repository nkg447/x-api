package com.xapi.language;

public class TranslationRequestBody {
    private String sourceLanguage;
    private String text;
    private String targetLanguage;

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getText() {
        return text;
    }

    TranslationRequestBody(String sL, String t, String tL) {
        sourceLanguage = sL;
        text = t;
        targetLanguage = tL;
    }
}
