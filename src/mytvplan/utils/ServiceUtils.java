package mytvplan.utils;

import mytvplan.model.BaseResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServiceUtils {

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    private final static String HOST_URL = "http://localhost:8080";

    public static BaseResponse execute(String params, Method method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(HOST_URL + params).openConnection();
        connection.setRequestMethod(method.name());
        return getResponse(connection);
    }

    public static BaseResponse execute(String params, Method method, String body) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(HOST_URL + params).openConnection();
        connection.setRequestMethod(method.name());

        OutputStream outputStream = connection.getOutputStream();
        byte[] input = body.getBytes(StandardCharsets.UTF_8);
        outputStream.write(input, 0, input.length);
        outputStream.flush();
        outputStream.close();

        return getResponse(connection);
    }

    private static BaseResponse getResponse(HttpURLConnection connection) throws IOException {
        connection.connect();

        int responseCode = connection.getResponseCode();
        InputStream inputStream;

        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null) {
            response.append(currentLine);
        }

        in.close();
        return new BaseResponse(response.toString());
    }

}
