package cn.whitrayhb.randomanimal;

import cn.whitrayhb.randomanimal.command.RandomCat;
import cn.whitrayhb.randomanimal.command.RandomDog;
import cn.whitrayhb.randomanimal.command.RandomFox;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class RandomAnimalMain extends JavaPlugin {
    public static final RandomAnimalMain INSTANCE = new RandomAnimalMain();
    private RandomAnimalMain() {
        super(new JvmPluginDescriptionBuilder("cn.whitrayhb.randomanimal", "0.1.2")
                .info("EG")
                .name("随机动物图插件")
                .author("WhitrayHB")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("随机动物图片插件加载成功！");
        CommandManager.INSTANCE.registerCommand(RandomCat.INSTANCE,true);
        CommandManager.INSTANCE.registerCommand(RandomDog.INSTANCE,true);
        CommandManager.INSTANCE.registerCommand(RandomFox.INSTANCE,true);
    }
}
