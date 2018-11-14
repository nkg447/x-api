package com.apis.azure.speech;

import com.xapi.speech.SpeechUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "AzureSynthesizeServlet")
public class AzureSynthesizeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String text = SpeechUtil.getTestFromRequest(request);

            InputStream voice = Synthesize.tts(text);

            ServletOutputStream stream = null;
            BufferedInputStream buf = null;
            try {
                stream = response.getOutputStream();

                //set response headers
                response.setContentType("audio/wav");

                buf = new BufferedInputStream(voice);
                int readBytes = 0;

                //read from the input stream; write to the ServletOutputStream
                while ((readBytes = buf.read()) != -1)
                    stream.write(readBytes);

                System.out.println("AzureSynthesizeServlet: response sent");

            } catch (IOException ioe) {
                throw new ServletException(ioe.getMessage());
            } finally {
                if (stream != null)
                    stream.close();
                if (buf != null)
                    buf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Only POST request are accepted.");
    }
}
