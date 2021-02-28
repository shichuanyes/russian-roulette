package com.github.shichuanyes.mirai.plugin

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.contact.Contact

object PluginData : AutoSavePluginData("Data") {
    val bulletPosition: MutableMap<Contact, Int> by value()
}