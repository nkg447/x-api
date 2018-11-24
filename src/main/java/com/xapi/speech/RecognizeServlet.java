package com.xapi.speech;

import com.apis.watson.speech.WatsonRecognizeServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecognizeServlet")
public class RecognizeServlet extends HttpServlet {

    static WatsonRecognizeServlet watsonRecognizeServlet = new WatsonRecognizeServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("SpeechRecognize with " + api + " API");

        if (api.equals("watson")) {
            watsonRecognizeServlet.doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
