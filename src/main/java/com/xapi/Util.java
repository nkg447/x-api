package com.xapi;

import com.google.common.io.CharStreams;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Util {
    /*
    * extract the request body from the request in utf-8
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        return CharStreams.toString(request.getReader());
    }

    public static String toSentence(String str) {
        str = str.trim();
        if (!str.endsWith(".")) {
            str = str + ".";
        }
        String firstChar = String.valueOf(str.charAt(0));

        return firstChar.toUpperCase() + str.substring(1);
    }
}
