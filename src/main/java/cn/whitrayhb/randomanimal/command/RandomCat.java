package cn.whitrayhb.randomanimal.command;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import cn.whitrayhb.randomanimal.data.CatData;
import cn.whitrayhb.randomanimal.data.FetchPicture;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class RandomCat extends JRawCommand {

    public static final RandomCat INSTANCE = new RandomCat();

    private RandomCat() {
        super(RandomAnimalMain.INSTANCE,"random-cat", "来只猫", "来只猫猫");
        setUsage("/random-cat"); // 设置用法，这将会在 /help 中展示
        setDescription("随机来一只猫猫"); // 设置描述，也会在 /help 中展示
        setPrefixOptional(true); // 设置指令前缀是可选的，即使用 `test` 也能执行指令而不需要 `/test`
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        sender.sendMessage("稍等……猫猫正在跑步前进!");
        String path = FetchPicture.fetchPicture(CatData.decodeJson(),"./data/cn.whitrayhb.randomanimal/cache/cat");
        if(path==null) {
            RandomAnimalMain.INSTANCE.getLogger().error("Image path is null");
            sender.sendMessage("猫猫迷路了……或许可以再试一次？");
            return;
        }
        File file = new File(path);
        if(sender.getSubject()!=null) {
            Image image = sender.getSubject().uploadImage(ExternalResource.create(file));
            sender.sendMessage(image);
        }else{
            sender.sendMessage("Subject is null, don't run this command in console");
        }
    }
}
