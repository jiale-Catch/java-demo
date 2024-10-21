package com.jiale.test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTes {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://moose-api.biaoxunkuaiche.com/service-api-search/api/v5/documents");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // 设置请求头，如Content-Type
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb21wYW55SWQiOiI0NyIsImNvbXBhbnlOYW1lIjoi5paw572RIn0.OJH9Lo0Ws_xqgpRQsz0TB6ZUrIE3jtASyAkDqehJq4g");

            // 发送请求参数
            String bodyString = "{\"searchWord\":\"安徽春风巴士广告有限公司\"}\n";
            byte[] outputInBytes = bodyString.getBytes("UTF-8");
            OutputStream os = conn.getOutputStream();
            os.write(outputInBytes);
            os.close();

            // 获取响应码
            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 处理响应
                java.util.Scanner s = new java.util.Scanner(conn.getInputStream()).useDelimiter("\\A");
                String response = s.hasNext() ? s.next() : "";
                System.out.println(response);
            } else {
                System.out.println("POST request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
