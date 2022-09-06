package cn.whitrayhb.randomanimal.data;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import static cn.whitrayhb.randomanimal.data.FetchJson.fetchJson;

@Deprecated
public class FoxData {
    /**
     * 从randomfox.ca拉取JSON并解析
     * @return 字符串，为狐狐图的链接
     */
    public static String getUrl(){//从random.cat拉取JSON并解析
        String json = fetchJson("https://randomfox.ca/floof/");
        if(json==null){
            RandomAnimalMain.INSTANCE.getLogger().error("获取到的JSON为空");
            return null;
        }
        String URL = null;
        try{
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.beginObject();
            if(reader.nextName()!=null) {
                URL = reader.nextString();
            }else return null;
            reader.skipValue();
            reader.skipValue();
            reader.endObject();
        }catch (Exception e){
            RandomAnimalMain.INSTANCE.getLogger().error("JSON解码失败");
            RandomAnimalMain.INSTANCE.getLogger().debug(e);
        }
        RandomAnimalMain.INSTANCE.getLogger().info("将要下载的图片地址是"+URL);
        return URL;
    }
}
