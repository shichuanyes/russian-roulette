package com.github.shichuanyes.mirai.plugin

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.permission.PermissionService.Companion.cancel
import net.mamoe.mirai.console.permission.PermissionService.Companion.permit
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
        logger.info { "Plugin russian-roulette loaded" }
        Roulette.register()
        AbstractPermitteeId.AnyContact.permit(Roulette.permission)
    }

    override fun onDisable() {
        AbstractPermitteeId.AnyContact.cancel(Roulette.permission, true)
        Roulette.unregister()
        logger.info { "Plugin russian-roulette unloaded" }
    }
}