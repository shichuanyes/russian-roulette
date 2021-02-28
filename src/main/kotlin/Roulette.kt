package com.github.shichuanyes.mirai.plugin

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import kotlin.random.Random

object Roulette : CompositeCommand(
    Main, primaryName = "roulette",
    secondaryNames = arrayOf("轮盘")
) {
    @SubCommand("开始")
    @Description("开始一局俄罗斯轮盘")
    suspend fun CommandSender.start(capacity: Int = 6) {
        val contact = subject ?: return

        if (PluginData.bulletPosition.containsKey(contact)) {
            sendMessage("已经有一场游戏在进行了")
            return
        }

        PluginData.bulletPosition[contact] = Random.nextInt(capacity)
        sendMessage("开始了")
    }

    @SubCommand("开枪")
    @Description("开几枪")
    suspend fun CommandSender.fire(n: Int = 1) {
        val contact = subject ?: return

        if (!PluginData.bulletPosition.containsKey(contact)) {
            sendMessage("没有进行中的游戏")
            return
        }

        PluginData.bulletPosition[contact]?.let {
            if (it - n < 0) {
                sendMessage("啪！你挂了")
                PluginData.bulletPosition.remove(contact)
            } else {
                sendMessage("还没挂")
                PluginData.bulletPosition[contact] = it - n
            }
        }
    }
}