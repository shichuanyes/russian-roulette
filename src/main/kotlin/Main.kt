package com.github.shichuanyes.mirai.plugin

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object Main : KotlinPlugin(
    JvmPluginDescription(
        id = "com.github.shichuanyes.russian-roulette",
        name = "russian-roulette",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
    }
}