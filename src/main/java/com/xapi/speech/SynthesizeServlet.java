package com.xapi.speech;

import com.apis.azure.speech.AzureSynthesizeServlet;
import com.apis.google.speech.GoogleSynthesizeServlet;
import com.apis.watson.speech.WatsonSynthesizeServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SynthesizeServlet")
public class SynthesizeServlet extends HttpServlet {

    static private WatsonSynthesizeServlet watsonSynthesizeServlet = new WatsonSynthesizeServlet();
    static private AzureSynthesizeServlet azureSynthesizeServlet = new AzureSynthesizeServlet();
    static private GoogleSynthesizeServlet googleSynthesizeServlet = new GoogleSynthesizeServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("SpeechSynthesize with " + api + " API");

        if (api.equals("watson")) {
            watsonSynthesizeServlet.doPost(request, response);
        } else if (api.equals("azure")) {
            azureSynthesizeServlet.doPost(request, response);
        }else{
            googleSynthesizeServlet.doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
