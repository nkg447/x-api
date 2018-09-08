package com.apis.google.language;

import com.google.cloud.translate.Detection;
import com.xapi.language.LanguageUtil;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoogleDetectServlet")
public class GoogleDetectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String text = LanguageUtil.getTestFromRequest(request);
            Detection detection = Detector.detectLanguage(text);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(detection);
            System.out.println("GoogleDetectServlet: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("GoogleDetectServlet: response sent");

        }catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(Detection detection) {
        JSONObject response = new JSONObject();
        response.put("detectedLanguage", detection.getLanguage());
        return String.valueOf(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
