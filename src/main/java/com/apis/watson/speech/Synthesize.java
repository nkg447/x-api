package com.apis.watson.speech;

import com.apis.Config;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

import java.io.IOException;
import java.io.InputStream;

public class Synthesize {
    static InputStream text_to_speech(String text) throws IOException {
        TextToSpeech service = new TextToSpeech();
//        create a Config.java file containing your username and password
        service.setUsernameAndPassword(Config.Watson.TextToSpeech.USERNAME, Config.Watson.TextToSpeech.PASSWORD);

        SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                .text(text)
                .voice(SynthesizeOptions.Voice.EN_US_ALLISONVOICE)
                .accept(SynthesizeOptions.Accept.AUDIO_WAV)
                .build();

        InputStream in = service.synthesize(synthesizeOptions).execute();

        return WaveUtils.reWriteWaveHeader(in);
    }

}
