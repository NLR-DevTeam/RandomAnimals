package cn.whitrayhb.randomanimal.command;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import cn.whitrayhb.randomanimal.data.CatData;
import cn.whitrayhb.randomanimal.data.FetchPicture;
import cn.whitrayhb.randomanimal.data.FoxData;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class RandomFox extends JRawCommand {

    public static final RandomFox INSTANCE = new RandomFox();

    private RandomFox() {
        super(RandomAnimalMain.INSTANCE,"random-fox", "来只狐", "来只狐狐","来只狐狸");
        setUsage("/random-cat"); // 设置用法，这将会在 /help 中展示
        setDescription("随机来一只狐狐"); // 设置描述，也会在 /help 中展示
        setPrefixOptional(true); // 设置指令前缀是可选的，即使用 `test` 也能执行指令而不需要 `/test`
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        sender.sendMessage("稍等……狐狐正在跑步前进!");
        String savePath = "./data/cn.whitrayhb.randomanimal/cache/fox";
        String url = FoxData.getUrl();
        String path = FetchPicture.fetchPicture(url,savePath);
        if(path==null) {
            RandomAnimalMain.INSTANCE.getLogger().error("图片路径为空");
            sender.sendMessage("狐狐找吃的去了……或许可以再试一次？");
            return;
        }
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
