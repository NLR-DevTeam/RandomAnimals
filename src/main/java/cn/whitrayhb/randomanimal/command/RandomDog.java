package cn.whitrayhb.randomanimal.command;

import cn.whitrayhb.randomanimal.RandomAnimalMain;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

public class RandomDog extends JRawCommand {

    public static final RandomDog INSTANCE = new RandomDog();

    private RandomDog() {
        super(RandomAnimalMain.INSTANCE,"random-dog", "来只狗", "来只狗勾","；来只狗狗");
        setUsage("/random-dog"); // 设置用法，这将会在 /help 中展示
        setDescription("随机来一只狗狗"); // 设置描述，也会在 /help 中展示
        setPrefixOptional(true); // 设置指令前缀是可选的，即使用 `test` 也能执行指令而不需要 `/test`
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        sender.sendMessage(buildMessage());
    }

    private MessageChain buildMessage(){

        return null;
    }
}
