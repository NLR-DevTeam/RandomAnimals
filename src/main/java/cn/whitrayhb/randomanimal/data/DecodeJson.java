package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import static cn.whitrayhb.randomanimal.data.FetchJson.fetchJson;

public class DecodeJson {
    public String decodeJson(String inurl){
        String json = fetchJson(inurl);
        if(json==null){
            RandomAnimalMain.INSTANCE.getLogger().error("获取到的JSON为空");
            return null;
        }
        String URL = null;
        try{
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.beginArray();
            reader.beginObject();
            reader.skipValue();
            reader.skipValue();
            if(reader.nextName()!=null) {
                URL = reader.nextString();
            }
            for(int i = 0;i<4;i++){
                reader.skipValue();
            }
            reader.endObject();
            reader.endArray();
        }catch (Exception e){
            RandomAnimalMain.INSTANCE.getLogger().error("JSON解码失败");
            RandomAnimalMain.INSTANCE.getLogger().debug(e);
        }
        RandomAnimalMain.INSTANCE.getLogger().info("将要下载的图片地址是"+URL);
        return URL;
    }
}
