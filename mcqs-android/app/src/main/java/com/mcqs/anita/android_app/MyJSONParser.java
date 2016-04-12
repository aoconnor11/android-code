package com.mcqs.anita.android_app;

import android.content.Context;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyJSONParser {
    static InputStream is = null;
    static JSONObject json = null;
    static String outPut = "";
    HttpURLConnection conn = null;
    Integer result = 0;
    private String questionIDTemp = "";
    Context context;
    private int status;
    private boolean httpType;
    private String userToken;

    public MyJSONParser() {
    }

    public MyJSONParser(String ids, Context context) {
        questionIDTemp = ids;
        this.context = context;
    }

    public MyJSONParser(Context context) {
        this.context = context;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isHttpType() {
        return httpType;
    }

    public void setHttpType(boolean httpType) {
        this.httpType = httpType;
    }

    public String getJSONFromUrl(String url) {
        try {
            URL myURL = new URL(url);
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer "
                    + userToken);
            if (httpType == true) {
                conn.setRequestMethod("POST");
            } else {
                conn.setRequestMethod("GET");
            }

            conn.connect();
            if (!questionIDTemp.equals("")) {
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(questionIDTemp);
                wr.flush();
            }
            int statusCode = conn.getResponseCode();
            setStatus(conn.getResponseCode());

            if (statusCode == 200) {
                StringBuilder sb = new StringBuilder();
                result = 1;
                try {
                    is = new BufferedInputStream(conn.getInputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            is));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    outPut = sb.toString();
                } catch (Exception e) {
                } finally {
                    is.close();
                    conn.disconnect();
                }
            } else if (statusCode == 401) {
            } else {
                result = 0;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPut;
    }
}
