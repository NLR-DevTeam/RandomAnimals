package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchJson {
    public static String fetchJson(String inURL){//从URL拉取JSON
        HttpURLConnection conn;
        InputStream is;
        BufferedReader br;
        StringBuilder result = new StringBuilder();
        try{
            java.net.URL url = new URL(inURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            String l;
            if(conn.getResponseCode()==200){
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                while ((l=br.readLine())!=null){
                    result.append(l);
                    return l;
                }
            }
            else{
                RandomAnimalMain.INSTANCE.getLogger().error("Exception occured with response code "+conn.getResponseCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
