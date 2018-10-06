package com.xapi.vision;

import com.apis.watson.vision.WatsonFaceDetectServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FaceDetectServlet")
public class FaceDetectServlet extends HttpServlet {

    static WatsonFaceDetectServlet watsonFaceDetectServlet = new WatsonFaceDetectServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String api = request.getParameter("api");
        System.out.println("FaceDetect with " + api + " API");
        if(api.equals("watson")){
            watsonFaceDetectServlet.doPost(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
