package cn.whitrayhb.randomanimal.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

class PluginConfig {
    object RandomAnimal : AutoSavePluginConfig("RandomAnimal") {
        @ValueDescription("CD时长，设为-1则禁用")
        val cooldown by value<Int>(15)
    }
}