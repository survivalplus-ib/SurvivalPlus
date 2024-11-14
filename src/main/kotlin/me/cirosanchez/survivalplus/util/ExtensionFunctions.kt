package me.cirosanchez.survivalplus.util

import me.cirosanchez.clib.getPlugin
import me.cirosanchez.clib.placeholder.Placeholder
import org.bukkit.Bukkit
import org.bukkit.event.Listener

fun Placeholder.apply(string: String): String {
    return string.replace(target, replacement)
}

fun registerListener(listener: Listener){
    Bukkit.getPluginManager().registerEvents(listener, getPlugin())
}