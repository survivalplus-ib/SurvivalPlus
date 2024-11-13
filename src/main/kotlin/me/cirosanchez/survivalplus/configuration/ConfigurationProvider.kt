package me.cirosanchez.survivalplus.configuration

import me.cirosanchez.clib.configuration.Configuration
import me.cirosanchez.clib.configuration.FileConfiguration

class ConfigurationProvider {
    lateinit var scoreboardConfiguration: Configuration


    fun load(){
        scoreboardConfiguration = FileConfiguration("scoreboard.yml").loadConfig()
    }
}