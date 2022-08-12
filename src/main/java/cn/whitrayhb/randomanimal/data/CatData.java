package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import static cn.whitrayhb.randomanimal.data.FetchJson.fetchJson;

public class CatData {
    public static String getUrl(){//从random.cat拉取JSON并解析
        String json = fetchJson("https://api.thecatapi.com/v1/images/search");
        if(json==null){
            RandomAnimalMain.INSTANCE.getLogger().error("JSON is null");
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
            RandomAnimalMain.INSTANCE.getLogger().error("Failed to decode JSON");
            RandomAnimalMain.INSTANCE.getLogger().error(e);
        }
        RandomAnimalMain.INSTANCE.getLogger().info("Picture's URL is "+URL);
        return URL;
    }
}
