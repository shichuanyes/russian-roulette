package com.github.shichuanyes.mirai.plugin

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object PluginData : AutoSavePluginData("Data") {
    val userBulletPosition: MutableMap<Long, Int> by value()
    val groupBulletPosition: MutableMap<Long, Int> by value()
}