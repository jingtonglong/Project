package com.ys.yarc.test;


import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", "12345679810");
        map.put("password", "123456");
        map.put("name", "112");
        sendPost("http://192.168.1.74:8080/user/app/register", map, "utf-8");
    }

    public static String sendPost(String urlParam, Map<String, String> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        JsonObject jsonObject =new JsonObject();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                try {
                    jsonObject.addProperty(e.getKey(), e.getValue());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            osw = new OutputStreamWriter(con.getOutputStream(), charset);
            osw.write(jsonObject.toString());
            osw.flush();
            // 读取返回内容
            resultBuffer = new StringBuffer();
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            System.out.println(resultBuffer.toString()+ "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
        }
        return resultBuffer.toString();
    }

}
