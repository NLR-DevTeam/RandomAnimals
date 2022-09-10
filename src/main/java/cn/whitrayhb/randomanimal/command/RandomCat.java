package cn.whitrayhb.randomanimal.command;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import cn.whitrayhb.randomanimal.config.PluginConfig;
import cn.whitrayhb.randomanimal.data.CatData;
import cn.whitrayhb.randomanimal.data.FetchPicture;
import cn.whitrayhb.randomanimal.util.Cooler;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.ConsoleCommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class RandomCat extends JRawCommand {

    public static final RandomCat INSTANCE = new RandomCat();

    private RandomCat() {
        super(RandomAnimalMain.INSTANCE,"random-cat", "来只猫", "来只猫猫");
        this.setUsage("(/)来只猫猫 #来一张猫猫图");
        this.setDescription("# 随机来一只猫猫");
        this.setPrefixOptional(true);
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        if(!(sender instanceof ConsoleCommandSender)){
            if (Cooler.isLocked(Objects.requireNonNull(sender.getUser()).getId())) {
                sender.sendMessage("操作太快了，请稍后再试");
                return;
            }
            Cooler.lock(sender.getUser().getId(), PluginConfig.RandomAnimal.INSTANCE.getCooldown());
        }
        sender.sendMessage("稍等……猫猫正在跑步前进!");
        /*图片保存路径*/
        String savePath = "./data/cn.whitrayhb.randomanimal/cache/cat/";
        /*图片链接*/
        String[] arrUrl = CatData.getUrl().split("/");
        String url = "https://static.jks.life/cat/images/" + arrUrl[arrUrl.length-1];
        RandomAnimalMain.INSTANCE.getLogger().info("将要下载的图片地址是"+url);
        /*下载图片并获取地址*/
        String path = FetchPicture.fetchPicture(url,savePath);
        if(path==null) {
            RandomAnimalMain.INSTANCE.getLogger().error("图片路径为空，在图片获取过程中出现问题！");
            sender.sendMessage("猫猫迷路了……或许可以再试一次？");
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
