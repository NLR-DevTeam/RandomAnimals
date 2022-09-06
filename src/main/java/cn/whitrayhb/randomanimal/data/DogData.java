package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import static cn.whitrayhb.randomanimal.data.FetchJson.fetchJson;

public class DogData {
    public static String getUrl(){
        String json = fetchJson("https://random.dog/woof");
        if(json==null){
            RandomAnimalMain.INSTANCE.getLogger().error("获取到的值为空");
            return null;
        }
        return "https://random.dog/"+json;
    }


    /**
     * 从random.dog拉取JSON并解析
     * @return 字符串，为狗狗图的链接
     */
    @Deprecated
    public static String getUrlOld(){//从random.dog拉取JSON并解析
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
        return URL;
    }
}
