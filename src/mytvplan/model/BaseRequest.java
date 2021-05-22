package mytvplan.model;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BaseRequest {

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    private final static String HOST_URL = "http://localhost:8080";

    public static String execute(String params, Method method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(HOST_URL + params).openConnection();
        connection.setRequestMethod(method.name());
        return getResponse(connection);
    }

    public static String execute(String params, Method method, String body) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(HOST_URL + params).openConnection();
        connection.setRequestMethod(method.name());
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        outputStream.close();

        return getResponse(connection);
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
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
        return response.toString();
    }

}
