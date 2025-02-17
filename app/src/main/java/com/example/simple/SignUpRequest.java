package com.example.simple;

import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SignUpRequest {

    public static String sendSignUpRequest(String fullname, String email, String username, String password) {
        String serverURL = "http://10.0.2.2/LoginRegister/signup.php";  // Change YOUR_LOCALHOST_PATH to your actual localhost URL
        try {
            // Prepare data to send
            HashMap<String, String> postData = new HashMap<>();
            postData.put("fullname", fullname);
            postData.put("email", email);
            postData.put("username", username);
            postData.put("password", password);

            // Convert data to URL encoded format
            StringBuilder postDataString = new StringBuilder();
            for (Map.Entry<String, String> entry : postData.entrySet()) {
                if (postDataString.length() != 0) postDataString.append("&");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    postDataString.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                }
                postDataString.append("=");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    postDataString.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                }
            }

            // Create connection
            URL url = new URL(serverURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // Send request
            OutputStream os = conn.getOutputStream();
            os.write(postDataString.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } catch (Exception e) {
            Log.e("SignUpRequest", "Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
