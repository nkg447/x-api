package com.apis.azure.vision;

import com.xapi.vision.VisionUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AzureFaceDetectServlet")
public class AzureFaceDetectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = VisionUtil.getUrlFromRequest(request);

            JSONArray detectedFaces = FaceDetect.faceDetect(url);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String jsonResponse = getJSONResponse(detectedFaces);
            System.out.println("AzureFaceDetect: response received");
            response.getWriter().println(jsonResponse);
            System.out.println("AzureFaceDetect: response sent");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private String getJSONResponse(JSONArray detectedFaces) {
        JSONObject result = new JSONObject();

        JSONArray faces = new JSONArray();

        JSONObject faceData;
        for (Object obj : detectedFaces) {
            JSONObject face = (JSONObject) obj;
            faceData = new JSONObject();

            JSONObject faceLocation = (JSONObject) face.get("faceRectangle");
            faceData.put("width", faceLocation.get("width"));
            faceData.put("height", faceLocation.get("height"));
            faceData.put("left", faceLocation.get("left"));
            faceData.put("top", faceLocation.get("top"));

            faces.add(faceData);
        }

        result.put("faces", faces);
        return String.valueOf(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
