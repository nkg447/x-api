package com.apis.watson.speech;

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "WatsonRecognizeServlet")
public class WatsonRecognizeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            InputStream audioInputStream = request.getInputStream();

            SpeechRecognitionResults results = Recognize.recognize(audioInputStream);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(results);

            response.getWriter().println(jsonResponse);
            System.out.println("WatsonRecognizeServlet: response sent");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(SpeechRecognitionResults results) {

        int size = 0;
        double confidence = 0;

        String transcript = "";

        SpeechRecognitionAlternative recognition;
        for (SpeechRecognitionResult result : results.getResults()) {
            if (result.isFinalResults()) {
                size++;
                recognition = result.getAlternatives().get(0);
                transcript += recognition.getTranscript();
                confidence += recognition.getConfidence();
            }
        }

        //avg confidence
        confidence = confidence / size;

        JSONObject response =new JSONObject();
        response.put("transcript", transcript);
        response.put("confidence", confidence);

        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
