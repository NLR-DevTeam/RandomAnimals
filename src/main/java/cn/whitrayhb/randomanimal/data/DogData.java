package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import static cn.whitrayhb.randomanimal.data.FetchJson.fetchJson;

public class DogData {
    /**
     * 从random.dog拉取JSON并解析
     * @return 字符串，为狗狗图的链接
     */
    public static String getUrl(){//从random.dog拉取JSON并解析
        String json = fetchJson("https://random.dog/woof.json");
        if(json==null){
            RandomAnimalMain.INSTANCE.getLogger().error("获取到的JSON为空");
            return null;
        }
        String URL = null;
        try{
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.beginObject();
            reader.skipValue();
            reader.skipValue();
                if(reader.nextName()!=null){
                   URL = reader.nextString();
                }
                else return null;
            reader.endObject();
        }catch (Exception e){
            RandomAnimalMain.INSTANCE.getLogger().error("JSON解码失败");
            RandomAnimalMain.INSTANCE.getLogger().debug(e);
        }
        RandomAnimalMain.INSTANCE.getLogger().info("将要下载的图片地址是"+URL);
        return URL;
    }
}
