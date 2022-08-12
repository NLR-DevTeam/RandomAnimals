package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchPicture {
    public static String fetchPicture(String inUrl,String path){
        HttpURLConnection httpUrl = null;
        String name = null;
        byte[] bytes = new byte[1024];
        int size = 0;
        try{
            URL url = new URL(inUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
            File file = new File(path);
            if(!file.exists()) file.mkdirs();
            String[] arrUrl = inUrl.split("/");
            name = arrUrl[arrUrl.length-1];
            FileOutputStream fos = new FileOutputStream(path+"/"+name);
            while ((size = bis.read(bytes)) != -1){
                fos.write(bytes, 0, size);
            }
            fos.close();
            bis.close();
            httpUrl.disconnect();
        }catch (Exception e){
            RandomAnimalMain.INSTANCE.getLogger().error("Failed to fetch picture!");
            RandomAnimalMain.INSTANCE.getLogger().error(e);
            return null;
        }
        RandomAnimalMain.INSTANCE.getLogger().info("Picture downloading is successful");
        return path+"/"+name;
    }
}
