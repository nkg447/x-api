package com.apis.watson.vision;

import com.apis.Config;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

import java.io.InputStream;

public class FaceDetect {

    static DetectedFaces faceDetect(String url) {
        VisualRecognition service = new VisualRecognition("2018-03-19");
        service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");

//        create a Config.java file containing your API_KEY
        IamOptions options = new IamOptions.Builder().apiKey(Config.Watson.Vision.API_KEY).build();
        service.setIamCredentials(options);

        DetectFacesOptions detectFacesOptions = new DetectFacesOptions.Builder()
                .url(url)
                .build();
        DetectedFaces result = service.detectFaces(detectFacesOptions).execute();

        return result;
    }

    static DetectedFaces faceDetect(InputStream inputStream) {
        VisualRecognition service = new VisualRecognition("2018-03-19");
        service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");

//        create a Config.java file containing your API_KEY
        IamOptions options = new IamOptions.Builder().apiKey(Config.Watson.Vision.API_KEY).build();
        service.setIamCredentials(options);

        DetectFacesOptions detectFacesOptions = new DetectFacesOptions.Builder()
                .imagesFile(inputStream)
                .build();
        DetectedFaces result = service.detectFaces(detectFacesOptions).execute();

        return result;
    }

}