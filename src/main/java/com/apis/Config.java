package com.apis;

public class Config {
    public static final class Azure{
        public static final class Language {
            public static final String SUBSCRIPTION_KEY = "<Key>";
        }

        public static final class TextToSpeech {
            public static final String SUBSCRIPTION_KEY = "<Key>";
        }

        public static final class SpeechToText {
            public static final String SUBSCRIPTION_KEY = "<Key>";
        }

        public static final class Vision {
            public static final String SUBSCRIPTION_KEY = "<Key>";
        }
    }

    public static final class Watson{
        public static final class Language {
            public static final String USERNAME = "<USERNAME>";
            public static final String PASSWORD = "<PASSWORD>";
        }

        public static final class TextToSpeech {
            public static final String USERNAME = "<USERNAME>";
            public static final String PASSWORD = "<PASSWORD>";
        }

        public static final class SpeechToText {
            public static final String USERNAME = "<USERNAME>";
            public static final String PASSWORD = "<PASSWORD>";
        }

        public static final class Vision {
            public static final String API_KEY = "<API_KEY>";
        }

    }
}