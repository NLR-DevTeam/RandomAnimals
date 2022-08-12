package org.example.mirai.plugin

import cn.whitrayhb.randomanimal.RandomAnimalMain
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader

suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()
    RandomAnimalMain.INSTANCE.load()
    RandomAnimalMain.INSTANCE.enable()
    MiraiConsole.job.join()
}