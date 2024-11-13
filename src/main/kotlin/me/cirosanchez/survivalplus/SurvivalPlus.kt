package me.cirosanchez.survivalplus

import me.cirosanchez.clib.cLib
import me.cirosanchez.clib.logger
import me.cirosanchez.survivalplus.configuration.ConfigurationProvider
import org.bukkit.plugin.java.JavaPlugin

class SurvivalPlus : JavaPlugin() {

    val configurationProvider = ConfigurationProvider()

    override fun onEnable() {
        cLib(this){
            this.messages = true
        }
        configurationProvider.load()


        logger.info("Plugin Enabled!")
    }

    override fun onDisable() {

    }
}
