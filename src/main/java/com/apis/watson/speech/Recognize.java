package com.apis.watson.speech;

import com.apis.Config;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Recognize {

    /*
     * Speech to Text using the Watson API
     */
    static SpeechRecognitionResults recognize(InputStream audioInputStream) throws FileNotFoundException {
        SpeechToText service = new SpeechToText();

//        create a Config.java file containing your username and password
        service.setUsernameAndPassword(Config.Watson.SpeechToText.USERNAME, Config.Watson.SpeechToText.PASSWORD);

        RecognizeOptions options = new RecognizeOptions.Builder()
                .audio(audioInputStream)
                .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
                .build();
        SpeechRecognitionResults transcript = service.recognize(options).execute();

        return transcript;
    }
}
