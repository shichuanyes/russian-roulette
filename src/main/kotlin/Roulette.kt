package com.github.shichuanyes.mirai.plugin

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.User
import kotlin.random.Random

object Roulette : CompositeCommand(
    Main, primaryName = "roulette",
    secondaryNames = arrayOf("轮盘")
) {
    @SubCommand("开始")
    @Description("开始一局俄罗斯轮盘")
    suspend fun CommandSender.start(capacity: Int = 6) {
        when (subject) {
            is User -> {
                val id = (subject as User).id
                if (PluginData.userBulletPosition.containsKey(id)) {
                    sendMessage("已经有一场游戏在进行了")
                    return
                }
                PluginData.userBulletPosition[id] = Random.nextInt(capacity)
                sendMessage("开始了")
            }
            is Group -> {
                val id = (subject as Group).id
                if (PluginData.groupBulletPosition.containsKey(id)) {
                    sendMessage("已经有一场游戏在进行了")
                    return
                }
                PluginData.groupBulletPosition[id] = Random.nextInt(capacity)
                sendMessage("开始了")
            }
            else -> {
                sendMessage("不支持的消息来源")
            }
        }
    }

    @SubCommand("开枪")
    @Description("开几枪")
    suspend fun CommandSender.fire(n: Int = 1) {
        when (subject) {
            is User -> {
                val id = (subject as User).id
                if (!PluginData.userBulletPosition.containsKey(id)) {
                    sendMessage("没有进行中的游戏")
                    return
                }

                PluginData.userBulletPosition[id]?.let {
                    if (it - n < 0) {
                        sendMessage("啪！你挂了")
                        PluginData.userBulletPosition.remove(id)
                    } else {
                        sendMessage("还没挂")
                        PluginData.userBulletPosition[id] = it - n
                    }
                }
            }
            is Group -> {
                val id = (subject as Group).id
                if (!PluginData.groupBulletPosition.containsKey(id)) {
                    sendMessage("没有进行中的游戏")
                    return
                }

                PluginData.groupBulletPosition[id]?.let {
                    if (it - n < 0) {
                        sendMessage("啪！你挂了")
                        PluginData.groupBulletPosition.remove(id)
                    } else {
                        sendMessage("还没挂")
                        PluginData.groupBulletPosition[id] = it - n
                    }
                }
            }
        }
    }
}