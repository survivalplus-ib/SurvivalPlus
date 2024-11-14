package me.cirosanchez.survivalplus

import me.cirosanchez.clib.cLib
import me.cirosanchez.clib.logger
import me.cirosanchez.survivalplus.configuration.ConfigurationProvider
import me.cirosanchez.survivalplus.listener.PlayerListener
import me.cirosanchez.survivalplus.util.registerListener
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin

class SurvivalPlus : JavaPlugin() {


    val configurationProvider = ConfigurationProvider()


    override fun onEnable() {
        instance = this
        cLib(instance){
            this.messages = true
        }
        configurationProvider.load()

        registerListener(PlayerListener())

        logger.info("Plugin Enabled!")
    }

    override fun onDisable() {

    }


    companion object {
        lateinit var instance: SurvivalPlus
        fun get() = instance
        fun mm() = MiniMessage.miniMessage()
    }
}
