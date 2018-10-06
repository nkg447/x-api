package com.apis.watson.vision;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Face;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.FaceLocation;
import com.xapi.vision.VisionUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WatsonFaceDetectServlet")
public class WatsonFaceDetectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = VisionUtil.getUrlFromRequest(request);

            DetectedFaces detectedFaces = FaceDetect.faceDetect(url);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(detectedFaces);
            System.out.println("WatsonFaceDetect: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("WatsonFaceDetect: response sent");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(DetectedFaces detectedFaces) {
        JSONObject result = new JSONObject();

        JSONArray faces = new JSONArray();

        JSONObject faceData;
        for (Face face : detectedFaces.getImages().get(0).getFaces()) {
            faceData = new JSONObject();

            FaceLocation faceLocation = face.getFaceLocation();
            faceData.put("width", faceLocation.getWidth());
            faceData.put("height", faceLocation.getHeight());
            faceData.put("left", faceLocation.getLeft());
            faceData.put("top", faceLocation.getTop());

            faces.add(faceData);
        }

        result.put("faces", faces);
        return String.valueOf(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
