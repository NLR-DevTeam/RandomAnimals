package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchPicture {
    /**
     * 下载图片
     * @param inUrl 将要下载的图片的链接
     * @param path 图片将要保存的位置
     * @return 字符串，为带文件名的图片位置
     */
    public static String fetchPicture(String inUrl,String path){
        HttpURLConnection httpUrl = null;
        byte[] bytes = new byte[4096];
        int size;
        String[] arrUrl = inUrl.split("/");
        String name = arrUrl[arrUrl.length-1];
        try{
            URL url = new URL(inUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.setConnectTimeout(3000);
            httpUrl.setReadTimeout(30000);
            httpUrl.connect();
            BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());
            FileOutputStream fos = new FileOutputStream(path+"/"+name);
            File file = new File(path);
            if(!file.exists()) file.mkdirs();
            while ((size = bis.read(bytes)) != -1){
                fos.write(bytes, 0, size);
            }
            fos.close();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e){
            RandomAnimalMain.INSTANCE.getLogger().error("图片下载失败!");
            RandomAnimalMain.INSTANCE.getLogger().error(e);
            return null;
        }
        RandomAnimalMain.INSTANCE.getLogger().info("图片下载成功！");
        return path+"/"+name;

    }
}
