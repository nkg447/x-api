package com.xapi.speech;

import com.apis.watson.speech.WatsonSynthesizeServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SynthesizeServlet")
public class SynthesizeServlet extends HttpServlet {

    WatsonSynthesizeServlet watsonSynthesizeServlet=new WatsonSynthesizeServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("SpeechSynthesize with " + api + " API");

        if (api.equals("watson")) {
            watsonSynthesizeServlet.doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
