package cn.whitrayhb.randomanimal.command;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import cn.whitrayhb.randomanimal.data.CatData;
import cn.whitrayhb.randomanimal.data.DogData;
import cn.whitrayhb.randomanimal.data.FetchPicture;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class RandomDog extends JRawCommand {

    public static final RandomDog INSTANCE = new RandomDog();

    private RandomDog() {
        super(RandomAnimalMain.INSTANCE,"random-dog", "来只狗", "来只狗勾","来只狗狗");
        this.setUsage("(/)来只狗狗 #来一张狗狗图"); // 设置用法，这将会在 /help 中展示
        this.setDescription("# 随机来一只狗狗"); // 设置描述，也会在 /help 中展示
        this.setPrefixOptional(true); // 设置指令前缀是可选的，即使用 `test` 也能执行指令而不需要 `/test`
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        sender.sendMessage("稍等……狗狗正在跑步前进!");
        String savePath;
        String url;
        String path;
        do {
            savePath = "./data/cn.whitrayhb.randomanimal/cache/dog";
            url = DogData.getUrl();
            RandomAnimalMain.INSTANCE.getLogger().info("将要下载的图片地址是"+url);
            path = FetchPicture.fetchPicture(url, savePath);
            if (path == null) {
                RandomAnimalMain.INSTANCE.getLogger().error("图片路径为空");
                sender.sendMessage("狗狗被路边的标志牌吸引住了……或许可以再试一次？");
                return;
            }
        }while(url.endsWith(".mp4"));
        File file = new File(path);
        if(sender.getSubject()!=null) {
            ExternalResource resource = ExternalResource.create(file);
            Image image = sender.getSubject().uploadImage(resource);
            sender.sendMessage(image);
            try {
                resource.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            sender.sendMessage("请不要在控制台中运行该命令");
        }
    }
}
